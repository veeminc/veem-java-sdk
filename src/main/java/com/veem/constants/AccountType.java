package com.veem.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType
{
    BUSINESS("Business"),
    INCOMPLETE("Incomplete"),
    PERSONAL("Personal");

    @JsonValue
    private String jsonValue;
}
