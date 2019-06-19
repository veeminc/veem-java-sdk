package com.veem.client.converters;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import com.veem.client.requests.AmountRequest;
import com.veem.client.responses.AmountResponse;
import com.veem.client.responses.PurposeOfPaymentResponse;
import com.veem.model.Amount;
import com.veem.model.PurposeOfPayment;

@NoArgsConstructor(access = PRIVATE)
public class PurposeOfPaymentConverter
{
    public static PurposeOfPayment convert(final PurposeOfPaymentResponse purposeOfPaymentResponse)
    {
        return PurposeOfPayment.builder()
                .description(purposeOfPaymentResponse.getDescription())
                .purposeCode(purposeOfPaymentResponse.getPurposeCode())
                .build();
    }
}
