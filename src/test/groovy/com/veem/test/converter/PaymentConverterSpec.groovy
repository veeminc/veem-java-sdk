package com.veem.test.converter

import com.neovisionaries.i18n.CountryCode
import com.neovisionaries.i18n.CurrencyCode
import com.veem.client.converters.PaymentConverter
import com.veem.client.requests.PaymentRequest
import com.veem.client.responses.PaymentResponse
import com.veem.constants.AccountType
import com.veem.constants.PaymentStatus
import com.veem.model.Account
import com.veem.model.Amount
import com.veem.model.Payment
import spock.lang.Specification

class PaymentConverterSpec  extends Specification
{
    def "Converts from PaymentResponse to Payment"()
    {
        given:
        PaymentResponse paymentResponse = new PaymentResponse(
                id: 1L,
                claimLink: "http://claimlink.com",
                status: PaymentStatus.IN_PROGRESS)

        when:
        Payment payment = PaymentConverter.convert(paymentResponse)

        then:
        noExceptionThrown()
        payment.status == PaymentStatus.IN_PROGRESS
    }

    def "Converts from Payment to PaymentRequest"()
    {
        given:
        Payment payment = Payment.builder()
            .claimLink("http://claimLink.com")
            .batchItemId(100L)
            .ccEmails(Arrays.asList("abcinc@test.com", "xyzinc@test.com"))
            .payee(Account.builder()
                    .firstName("john")
                    .lastName("smith")
                    .email("jsmith@test.com")
                    .countryCode(CountryCode.US)
                    .businessName("XYZ Inc")
                    .build())
            .amount(new Amount(BigDecimal.valueOf(1000), CurrencyCode.USD))
        .build()

        when:
        PaymentRequest paymentRequest = PaymentConverter.convert(payment)

        then:
        noExceptionThrown()
    }
}
