package com.veem.client.converters;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import com.veem.client.requests.ExchangeRateRequest;
import com.veem.client.responses.ExchangeRateResponse;
import com.veem.model.ExchangeRate;

@NoArgsConstructor(access = PRIVATE)
public class ExchangeRateConverter
{
    public static ExchangeRate convert(final ExchangeRateResponse exchangeRateResponse)
    {
        return ExchangeRate.builder()
                .hashId(exchangeRateResponse.getId())
                .fromAmount(exchangeRateResponse.getFromAmount())
                .fromCurrency(exchangeRateResponse.getFromCurrency())
                .fromCountry(exchangeRateResponse.getFromCountry())
                .toAmount(exchangeRateResponse.getToAmount())
                .toCurrency(exchangeRateResponse.getToCurrency())
                .toCountry(exchangeRateResponse.getToCountry())
                .rate(exchangeRateResponse.getRate())
                .expiry(exchangeRateResponse.getExpiry())
                .build();
    }

    public static ExchangeRateRequest convert(final ExchangeRate exchangeRate)
    {
        return ExchangeRateRequest.builder()
                .fromAmount(exchangeRate.getFromAmount())
                .fromCurrency(exchangeRate.getFromCurrency())
                .fromCountry(exchangeRate.getFromCountry())
                .toAmount(exchangeRate.getToAmount())
                .toCurrency(exchangeRate.getToCurrency())
                .toCountry(exchangeRate.getToCountry())
                .build();
    }
}
