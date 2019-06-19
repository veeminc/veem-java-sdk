package com.veem.test.converter

import com.neovisionaries.i18n.CountryCode
import com.neovisionaries.i18n.CurrencyCode
import com.veem.client.converters.ExchangeRateConverter
import com.veem.client.requests.ExchangeRateRequest
import com.veem.client.responses.ExchangeRateResponse
import com.veem.model.ExchangeRate
import spock.lang.Specification

import java.time.Instant

class ExchangeRateConverterSpec extends Specification
{
    def "Converts from ExchangeRateResponse to ExchangeRate"()
    {
        given:
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse(
                id : UUID.toString(),
                fromAmount: 5000.0,
                fromCurrency: CurrencyCode.USD,
                fromCountry: CountryCode.US,
                toAmount: 7500,
                toCurrency: CurrencyCode.CAD,
                toCountry: CountryCode.CA,
                rate: 1.5,
                expiry: Date.from(Instant.now())
        )

        when:
        ExchangeRate exchangeRate = ExchangeRateConverter.convert(exchangeRateResponse)

        then:
        noExceptionThrown()
        exchangeRate.toAmount == 7500
        exchangeRate.rate == 1.5
    }

    def "Converts from ExchangeRate to ExchangeRateRequest"()
    {
        given:
        ExchangeRate exchangeRate =  ExchangeRate.builder()
                .fromAmount(1000)
                .fromCurrency(CurrencyCode.USD)
                .fromCountry(CountryCode.US)
                .toAmount(1015)
                .toCurrency(CurrencyCode.CAD)
                .toCountry(CountryCode.CA)
                .hashId(UUID.toString())
                .build()

        when:
        ExchangeRateRequest exchangeRateRequest = ExchangeRateConverter.convert(exchangeRate)

        then:
        noExceptionThrown()
        exchangeRateRequest.toAmount == 1015
        exchangeRateRequest.fromAmount == 1000
    }
}
