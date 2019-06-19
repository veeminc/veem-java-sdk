package com.veem.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The direction of a business transaction.
 */
@AllArgsConstructor
@Getter
public enum Direction
{
    /**
     * Direction of transaction for which the current account is the receiver of funds.
     */
    INBOUND("Inbound"),

    /**
     * Direction of transaction for which the current account is the sender of funds.
     */
    OUTBOUND("Outbound");

    private String stringValue;
}
