package com.veem.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus
{
    DRAFTED("Drafted"),
    SCHEDULED("Scheduled"),
    PENDING_APPROVAL("PendingApproval"),
    SENT("Sent"),
    CLAIMED("Claimed"),
    PENDING_AUTH("PendingAuth"),
    AUTHORIZED("Authorized"),
    IN_PROGRESS("InProgress"),
    COMPLETE("Complete"),
    CANCELLED("Cancelled"),
    CLOSED("Closed"),
    REVERSED("Reversed");

    @JsonValue
    private String jsonValue;
}
