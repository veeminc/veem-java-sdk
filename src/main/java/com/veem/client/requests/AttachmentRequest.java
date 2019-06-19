package com.veem.client.requests;

import lombok.Builder;
import lombok.Getter;

import com.veem.constants.AttachmentType;

@Builder
@Getter
public class AttachmentRequest
{
    private AttachmentType type;
    private String name;
    private String referenceId;
}
