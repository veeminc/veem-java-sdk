package com.veem.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentSortField
{
    TIME_UPDATE("timeUpdated");

    private String stringValue;
}
