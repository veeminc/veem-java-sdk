package com.veem.model;

import lombok.Builder;
import lombok.Getter;

import java.nio.file.Path;

import com.veem.constants.AttachmentType;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Attachment
{
    private AttachmentType type;
    private String name;
    private String referenceId;
    private Path path;
}
