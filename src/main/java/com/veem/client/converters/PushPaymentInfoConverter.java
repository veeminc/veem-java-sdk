package com.veem.client.converters;

import com.veem.client.responses.PushPaymentInfoResponse;
import com.veem.model.PushPaymentInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PushPaymentInfoConverter
{
    public static PushPaymentInfo convert(final PushPaymentInfoResponse pushPaymentInfoResponse)
    {
        return PushPaymentInfo.builder()
                .amount(AmountConverter.convert(pushPaymentInfoResponse.getAmount()))
                .reference(pushPaymentInfoResponse.getReference())
                .pushPaymentInfo(pushPaymentInfoResponse.getPushPaymentInfo())
                .build();
    }
}
