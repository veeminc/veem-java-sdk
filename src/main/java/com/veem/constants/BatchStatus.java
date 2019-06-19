package com.veem.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BatchStatus
{
    FAILED("Failed"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Complete");

    @JsonValue
    private String jsonValue;
}
