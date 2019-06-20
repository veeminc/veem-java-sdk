package com.veem.test

import static com.neovisionaries.i18n.CountryCode.CN
import static com.neovisionaries.i18n.CountryCode.DE
import static com.neovisionaries.i18n.CountryCode.US
import static com.neovisionaries.i18n.CurrencyCode.EUR
import static com.neovisionaries.i18n.CurrencyCode.USD
import static com.veem.client.VeemContext.Environment.SANDBOX
import static com.veem.constants.AccountType.INCOMPLETE
import static com.veem.constants.PaymentStatus.CANCELLED
import static com.veem.constants.PaymentStatus.DRAFTED
import static com.veem.constants.PaymentStatus.SENT
import static java.util.UUID.randomUUID

import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import com.veem.client.VeemClient
import com.veem.client.VeemContext
import com.veem.model.*

class PaymentClientSpec extends Specification
{
    @Shared config = new JsonSlurper().parse(getClass().getResource( "testConfig.json" ))
    @Shared VeemContext veemContext = new VeemContext.VeemContextBuilder()
            .environment(SANDBOX)
            .build()
    @Shared VeemClient veemClient = veemContext.getClient(config.token)

    def "+ List payments, no parameters"()
    {
        given:
        PaymentListParameters parameters = PaymentListParameters.builder().build()

        when: "Fetching an existing payment"
        Page<Payment> payments = veemClient.payments().list(parameters)

        then: "Returned payment is non-null and has the requested ID"
        noExceptionThrown()
    }

    def "+ Send payment to US in USD"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                    .email("test-${-> randomUUID()}@mailinator.com")
                    .firstName("Test")
                    .lastName("Veem API")
                    .countryCode(US)
                    .phone("2102102100")
                    .type(INCOMPLETE)
                    .build())
                .build()

        when:
        Payment sentPayment = veemClient.payments().send(payment)

        then:
        noExceptionThrown()
        sentPayment.getStatus() == SENT
    }

    def "+ Send payment to Germany in EUR"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), EUR))
                .payee(Account.builder()
                    .email("test-${-> randomUUID()}@mailinator.com")
                    .firstName("Test")
                    .lastName("Veem API")
                    .countryCode(DE)
                    .phone("2102102100")
                    .type(INCOMPLETE)
                    .build())
                .build()

        when:
        Payment sentPayment = veemClient.payments().send(payment)

        then:
        noExceptionThrown()
        sentPayment.getStatus() == SENT
    }

    def "+ Send payment to Germany in USD"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                    .email("test-${-> randomUUID()}@mailinator.com")
                    .firstName("Test")
                    .lastName("Veem API")
                    .countryCode(DE)
                    .phone("2102102100")
                    .type(INCOMPLETE)
                    .build())
                .build()

        when:
        Payment sentPayment = veemClient.payments().send(payment)

        then:
        noExceptionThrown()
        sentPayment.getStatus() == SENT
    }

    def "+ Draft payment"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                    .email("test-${-> randomUUID()}@mailinator.com")
                    .firstName("Test")
                    .lastName("Veem API")
                    .countryCode(US)
                    .phone("2102102100")
                    .type(INCOMPLETE)
                    .build())
                .build()

        when:
        Payment draftedPayment = veemClient.payments().draft(payment)

        then:
        noExceptionThrown()
        draftedPayment.getStatus() == DRAFTED
    }

    def "+ Send drafted payment"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                .email("test-${-> randomUUID()}@mailinator.com")
                .firstName("Test")
                .lastName("Veem API")
                .countryCode(US)
                .phone("2102102100")
                .type(INCOMPLETE)
                .build())
                .build()
        Payment draftedPayment = veemClient.payments().draft(payment)

        when:
        Payment sentPayment = veemClient.payments().send(draftedPayment.getId())

        then:
        noExceptionThrown()
        sentPayment.getStatus() == SENT
    }

    def "+ Cancel drafted payment"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                .email("test-${-> randomUUID()}@mailinator.com")
                .firstName("Test")
                .lastName("Veem API")
                .countryCode(US)
                .phone("2102102100")
                .type(INCOMPLETE)
                .build())
                .build()
        Payment draftedPayment = veemClient.payments().draft(payment)

        when:
        Payment cancelledPayment = veemClient.payments().cancel(draftedPayment.getId())

        then:
        noExceptionThrown()
        cancelledPayment.getStatus() == CANCELLED
    }

    def "+ Cancel sent payment"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                .email("test-${-> randomUUID()}@mailinator.com")
                .firstName("Test")
                .lastName("Veem API")
                .countryCode(US)
                .phone("2102102100")
                .type(INCOMPLETE)
                .build())
                .build()
        Payment draftedPayment = veemClient.payments().draft(payment)
        Payment sentPayment = veemClient.payments().send(draftedPayment.getId())

        when:
        Payment cancelledPayment = veemClient.payments().cancel(sentPayment.getId())

        then:
        noExceptionThrown()
        cancelledPayment.getStatus() == CANCELLED
    }

    def "+ Get drafted payment"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                .email("test-${-> randomUUID()}@mailinator.com")
                .firstName("Test")
                .lastName("Veem API")
                .countryCode(US)
                .phone("2102102100")
                .type(INCOMPLETE)
                .build())
                .build()
        Payment draftedPayment = veemClient.payments().draft(payment)

        when:
        Payment fetchedPayment = veemClient.payments().get(draftedPayment.getId())

        then:
        noExceptionThrown()
        fetchedPayment.getStatus() == DRAFTED
    }

    def "+ Get purpose of payment, US"()
    {
        when:
        veemClient.payments().purposeOfPaymentValues(US)

        then:
        noExceptionThrown()
    }

    def "+ Get purpose of payment, CN"()
    {
        when:
        veemClient.payments().purposeOfPaymentValues(CN)

        then:
        noExceptionThrown()
    }

    def "+ Send payment batch"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                .email("test-${-> randomUUID()}@mailinator.com")
                .firstName("Test")
                .lastName("Veem API")
                .countryCode(US)
                .phone("2102102100")
                .type(INCOMPLETE)
                .build())
                .batchItemId(new Random().nextLong())
                .build()

        when:
        Batch batch = veemClient.payments().createBatch([payment], true)

        then:
        noExceptionThrown()
        batch.batchItems[0].id == payment.batchItemId
    }

    def "+ Get payment batch"()
    {
        given:
        Payment payment = Payment.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payee(Account.builder()
                .email("test-${-> randomUUID()}@mailinator.com")
                .firstName("Test")
                .lastName("Veem API")
                .countryCode(US)
                .phone("2102102100")
                .type(INCOMPLETE)
                .build())
                .batchItemId(new Random().nextLong())
                .build()
        Batch sentBatch = veemClient.payments().createBatch([payment], true)

        when:
        Batch batch = veemClient.payments().getBatch(sentBatch.id)

        then:
        noExceptionThrown()
        batch.batchItems[0].id == payment.batchItemId
    }

    def cleanup()
    {
        sleep(1000)
    }
}
