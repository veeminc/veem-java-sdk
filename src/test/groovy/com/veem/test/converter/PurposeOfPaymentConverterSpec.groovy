package com.veem.test.converter

import com.veem.client.converters.PurposeOfPaymentConverter
import com.veem.client.responses.PurposeOfPaymentResponse
import spock.lang.Specification

class PurposeOfPaymentConverterSpec extends Specification
{
    def "Converts from PurposeOfPaymentResponse to PurposeOfPayment"()
    {
        given:
        PurposeOfPaymentResponse purposeOfPaymentResponse =
                new PurposeOfPaymentResponse(purposeCode: "Service", description: "Services")

        when:
        PurposeOfPaymentConverter.convert(purposeOfPaymentResponse)

        then:
        noExceptionThrown()
    }
}
