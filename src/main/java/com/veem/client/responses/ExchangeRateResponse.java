package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse
{
    private String Id;
    private Double fromAmount;
    private CurrencyCode fromCurrency;
    private CountryCode fromCountry;
    private Double toAmount;
    private CurrencyCode toCurrency;
    private CountryCode toCountry;
    private Double rate;
    private Date expiry;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private Date timeCreated;
}
