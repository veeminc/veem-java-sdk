package com.veem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PushPaymentInfo
{
    private Amount amount;
    private String reference;
    private String pushPaymentInfo;
}
