package com.veem.test.converter

import com.neovisionaries.i18n.CountryCode
import com.neovisionaries.i18n.CurrencyCode
import com.veem.client.converters.CountryInfoConverter
import com.veem.client.responses.CountryInfoResponse
import com.veem.model.CountryInfo
import spock.lang.Specification

class CountryInfoConverterSpec extends Specification
{
    def "Converts from CountryCodeResponse to CountryInfo"()
    {
        def sendingCurrencies = [CurrencyCode.INR, CurrencyCode.USD, CurrencyCode.EUR]
        def receivingCurrencies = [CurrencyCode.USD, CurrencyCode.EUR]

        given:
        CountryInfoResponse countryInfoResponse = new CountryInfoResponse(
                country: CountryCode.US,
                sendingCurrencies: sendingCurrencies,
                receivingCurrencies: receivingCurrencies,
                bankFields: null,
                purposeOfPaymentInfo: null,
        )

        when:
        CountryInfo countryInfo = CountryInfoConverter.convert(countryInfoResponse)

        then:
        noExceptionThrown()
        countryInfo.receivingCurrencies == receivingCurrencies
        countryInfo.sendingCurrencies == sendingCurrencies
    }
}
