package com.veem.client.converters;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import java.nio.file.Path;

import com.veem.client.requests.AttachmentRequest;
import com.veem.client.responses.AttachmentResponse;
import com.veem.model.Attachment;

@NoArgsConstructor(access = PRIVATE)
public class AttachmentConverter
{
    public static Attachment convert(final AttachmentResponse attachmentResponse, final Path filePath)
    {
        return Attachment.builder()
                .name(attachmentResponse.getName())
                .referenceId(attachmentResponse.getReferenceId())
                .type(attachmentResponse.getType())
                .path(filePath)
                .build();
    }

    public static Attachment convert(final Attachment attachment, final Path downloadPath)
    {
        return Attachment.builder()
                .name(attachment.getName())
                .referenceId(attachment.getReferenceId())
                .type(attachment.getType())
                .path(downloadPath)
                .build();
    }

    public static AttachmentRequest convert(final Attachment attachment)
    {
        return AttachmentRequest.builder()
                .name(attachment.getName())
                .referenceId(attachment.getReferenceId())
                .build();
    }

    public static Attachment convert(final AttachmentResponse attachmentResponse)
    {
        return Attachment.builder()
                .name(attachmentResponse.getName())
                .referenceId(attachmentResponse.getReferenceId())
                .type(attachmentResponse.getType())
                .build();
    }
}
