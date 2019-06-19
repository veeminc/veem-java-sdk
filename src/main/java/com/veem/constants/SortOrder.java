package com.veem.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortOrder
{
    ASC("asc"),
    DESC("desc");

    private String stringValue;
}
