package com.veem.test.converter

import com.neovisionaries.i18n.CurrencyCode
import com.veem.client.converters.AmountConverter
import com.veem.client.requests.AmountRequest
import com.veem.client.responses.AmountResponse
import com.veem.model.Amount
import spock.lang.Specification

class AmountConverterSpec extends Specification
{
    def  "Converts from AmountResponse to Amount"()
    {
        given:
        AmountResponse amountResponse = new AmountResponse(
                number: BigDecimal.valueOf(1L),
                currency: CurrencyCode.INR
        )

        when:
        Amount amount = AmountConverter.convert(amountResponse)

        then:
        amount != null
        amount.number == 1L
        amount.currency == CurrencyCode.INR
    }

    def "Converts form Amount to AmountRequest"()
    {
        given:
        Amount amount = Amount.builder()
            .number(BigDecimal.valueOf(1L))
            .currency(CurrencyCode.INR)
            .build()

        when:
        AmountRequest amountRequest = AmountConverter.convert(amount)

        then:
        amountRequest != null
        amountRequest.number == 1L
        amountRequest.currency == CurrencyCode.INR
    }
}
