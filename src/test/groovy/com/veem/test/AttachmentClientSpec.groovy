package com.veem.test

import static com.veem.client.VeemContext.Environment.SANDBOX

import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.veem.client.VeemClient
import com.veem.client.VeemContext
import com.veem.model.Attachment

class AttachmentClientSpec extends Specification
{
    @Shared config = new JsonSlurper().parse(getClass().getResource( "testConfig.json" ))
    @Shared VeemContext veemContext = new VeemContext.VeemContextBuilder()
            .environment(SANDBOX)
            .build()
    @Shared VeemClient veemClient = veemContext.getClient(config.token)

    def "+ UploadAttachment"()
    {
        given:
        Path file = Paths.get(getClass().getResource( "forUpload.png" ).toURI())
        Attachment attachment = Attachment.builder().path(file).build()

        when:
        Attachment uploadedAttachment = veemClient.attachments().upload(attachment)

        then:
        noExceptionThrown()
        uploadedAttachment.name.equals("forUpload.png")
        uploadedAttachment.path.equals(file)
        uploadedAttachment.referenceId != null
    }

    def "+ DownloadAttachment"()
    {
        given:
        Path file = Paths.get(getClass().getResource( "forUpload.png" ).toURI())
        Attachment attachment = Attachment.builder().path(file).build()
        Attachment uploadedAttachment = veemClient.attachments().upload(attachment)
        Path downloadDir = Files.createTempDirectory(UUID.randomUUID().toString())

        when:
        Attachment downloadedAttachment = veemClient.attachments().download(uploadedAttachment, downloadDir)

        then:
        noExceptionThrown()
        downloadedAttachment.name.equals(uploadedAttachment.getName())
        downloadedAttachment.referenceId.equals(uploadedAttachment.getReferenceId())
        downloadedAttachment.path.equals(downloadDir.resolve(downloadedAttachment.getName()))
        Files.isReadable(downloadedAttachment.path)
    }

    def cleanup()
    {
        sleep(2000)
    }
}
