package com.veem.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApprovalStatus
{
    APPROVED("Approved"),
    PENDING("Pending"),
    IGNORED("Ignored");

    @JsonValue
    private String value;
}
