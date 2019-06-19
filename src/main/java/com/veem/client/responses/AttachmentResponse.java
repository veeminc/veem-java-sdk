package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import com.veem.constants.AttachmentType;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentResponse
{
    private AttachmentType type;
    private String name;
    private String referenceId;
}
