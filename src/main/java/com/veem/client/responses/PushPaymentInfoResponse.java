package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushPaymentInfoResponse
{
    private AmountResponse amount;
    private String reference;
    private String pushPaymentInfo;
}
