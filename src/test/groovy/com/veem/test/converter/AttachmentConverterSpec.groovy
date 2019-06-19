package com.veem.test.converter

import com.veem.client.converters.AttachmentConverter
import com.veem.client.requests.AttachmentRequest
import com.veem.client.responses.AttachmentResponse
import com.veem.constants.AttachmentType
import com.veem.model.Attachment
import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

class AttachmentConverterSpec extends  Specification
{
    static final ATTACHMENT_FILE = "attach.png"
    static final FILE_PATH = "file:///" + ATTACHMENT_FILE
    static final REFRENCE_ID = "reference_id"

    def "Converts from attachmentResponse to Attachment"()
    {
        given:
        AttachmentResponse attachmentResponse = new AttachmentResponse(
                type : AttachmentType.PROOF_OF_PAYMENT,
                name : ATTACHMENT_FILE,
                referenceId: REFRENCE_ID
        )
        Path filePath = Paths.get(FILE_PATH)

        when:
        Attachment attachment = AttachmentConverter.convert(attachmentResponse, filePath)

        then:
        noExceptionThrown()
        attachment.path == filePath
        attachment.type == AttachmentType.PROOF_OF_PAYMENT
    }

    def "Converts from Attachement to AttachmentRequest"()
    {
        given:
        Attachment attachment = new Attachment(AttachmentType.PROOF_OF_PAYMENT,
            ATTACHMENT_FILE, REFRENCE_ID, Paths.get(FILE_PATH))

        when:
        AttachmentRequest attachmentRequest = AttachmentConverter.convert(attachment)

        then:
        noExceptionThrown()
        attachmentRequest.referenceId == REFRENCE_ID
        attachmentRequest.name == ATTACHMENT_FILE
    }

}
