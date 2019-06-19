package com.veem.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InvoiceStatus
{
    DRAFTED("Drafted"),
    SENT("Sent"),
    CANCELLED("Cancelled"),
    CLOSED("Closed"),
    CLAIMED("Claimed"),
    PAID("MarkAsPaid"),
    REJECTED("Rejected");

    @JsonValue
    private String jsonValue;
}
