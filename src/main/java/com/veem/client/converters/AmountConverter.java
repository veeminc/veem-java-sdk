package com.veem.client.converters;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import com.veem.client.requests.AmountRequest;
import com.veem.client.responses.AmountResponse;
import com.veem.model.Amount;

@NoArgsConstructor(access = PRIVATE)
public class AmountConverter
{
    public static Amount convert(final AmountResponse amountResponse)
    {
        return Amount.builder()
                .number(amountResponse.getNumber())
                .currency(amountResponse.getCurrency())
                .build();
    }

    public static AmountRequest convert(final Amount amount)
    {
        return AmountRequest.builder()
                .number(amount.getNumber())
                .currency(amount.getCurrency())
                .build();
    }
}
