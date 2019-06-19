package com.veem.client;

import static com.veem.client.ResponseHandler.handleResponse;
import static com.veem.client.converters.AttachmentConverter.convert;
import static java.util.Optional.ofNullable;

import lombok.NonNull;
import lombok.val;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.veem.exceptions.VeemException;
import com.veem.exceptions.VeemSdkException;
import com.veem.model.Attachment;
import com.veem.model.Invoice;
import com.veem.model.Payment;

public final class AttachmentClient
{
    private final VeemClient veemClient;
    private final AttachmentApi attachmentApi;

    AttachmentClient(VeemClient veemClient)
    {
        this.veemClient = veemClient;
        this.attachmentApi = veemClient.context.attachmentApi;
    }

    /**
     * Upload an attachment to Veem. The attachment can be referenced from other entities,
     * like {@link Payment} and {@link Invoice}.
     *
     * @param attachment An {@link Attachment} with a valid {@link Attachment#path path} to a readable file
     * @return A new {@link Attachment} with {@link Attachment#name name} and
     *         {@link Attachment#referenceId referenceId} set
     * @throws VeemException If the provided {@link Attachment#path} is invalid, or if uploading fails.
     */
    public Attachment upload(@NonNull final Attachment attachment) throws VeemException
    {
        if (!Files.exists(attachment.getPath()) || !Files.isRegularFile(attachment.getPath()))
        {
            throw new VeemSdkException("The provided path does not reference a file");
        }
        String contentType;
        try
        {
            contentType = ofNullable(Files.probeContentType(attachment.getPath())).orElse("multipart/form-data");
        }
        catch (IOException e)
        {
            contentType = "multipart/form-data";
        }
        val requestBody = RequestBody.create(MediaType.parse(contentType), attachment.getPath().toFile());
        val filePart = Part.createFormData("file", attachment.getPath().getFileName().toString(), requestBody);
        val description = RequestBody.create(MultipartBody.FORM, "Attachment");
        val response = handleResponse(attachmentApi.upload(description, filePart, veemClient.authorization)::execute);
        return convert(response, attachment.getPath());
    }

    /**
     * Download an attachment from Veem.
     *
     * @param attachment An {@link Attachment} with a valid {@link Attachment#name name}
     *                   and {@link Attachment#referenceId referenceId}
     * @param targetDirectory The directory where the downloaded file will be stored
     * @return A new {@link Attachment} with {@link Attachment#path path} set to the downloaded file
     * @throws VeemException If the provided {@code targetDirectory} is invalid, or if downloading fails.
     */
    public Attachment download(final Attachment attachment, final Path targetDirectory) throws VeemException
    {
        prepareDirectory(targetDirectory);
        val targetFilePath = targetDirectory.toAbsolutePath().resolve(attachment.getName());
        if (Files.exists(targetFilePath))
        {
            throw new VeemSdkException(String.format("%s already exists", targetFilePath.toString()));
        }
        val responseBody = handleResponse(attachmentApi.download(
                attachment.getName(), attachment.getReferenceId(), veemClient.authorization)::execute);
        writeFile(targetFilePath, responseBody);
        return convert(attachment, targetFilePath);
    }

    private static void prepareDirectory(final Path targetDirectory) throws VeemException
    {
        if (Files.exists(targetDirectory))
        {
            if (!Files.isDirectory(targetDirectory))
            {
                throw new VeemSdkException(String.format("%s is not a directory", targetDirectory.toString()));
            }
        }
        else
        {
            if (!targetDirectory.toFile().mkdirs())
            {
                throw new VeemSdkException(String.format("Unable to create director %s", targetDirectory.toString()));
            }
        }
    }

    private static void writeFile(final Path targetFilePath, final ResponseBody responseBody) throws VeemSdkException
    {
        try (val inputStream = responseBody.byteStream())
        {
            Files.copy(inputStream, targetFilePath);
        }
        catch (IOException e)
        {
            throw new VeemSdkException(e, String.format("The downloaded file could not be saved to %s",
                    targetFilePath.toString()));
        }
    }
}
