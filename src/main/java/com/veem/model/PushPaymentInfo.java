package com.veem.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PushPaymentInfo
{
    private Amount amount;
    private String reference;
    private String pushPaymentInfo;
}
