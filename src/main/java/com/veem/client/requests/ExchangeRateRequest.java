package com.veem.client.requests;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ExchangeRateRequest
{
    private Double fromAmount;
    private CurrencyCode fromCurrency;
    private CountryCode fromCountry;
    private Double toAmount;
    private CurrencyCode toCurrency;
    private CountryCode toCountry;
    private Double rate;
    private Date expiry;
    private Date timeCreated;
}
