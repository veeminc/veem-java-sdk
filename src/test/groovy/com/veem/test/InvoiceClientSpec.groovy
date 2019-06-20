package com.veem.test

import static com.neovisionaries.i18n.CountryCode.US
import static com.neovisionaries.i18n.CurrencyCode.USD
import static com.veem.client.VeemContext.Environment.SANDBOX
import static com.veem.constants.AccountType.INCOMPLETE
import static com.veem.constants.InvoiceStatus.CANCELLED
import static com.veem.constants.InvoiceStatus.SENT
import static java.util.UUID.randomUUID

import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import com.veem.client.VeemClient
import com.veem.client.VeemContext
import com.veem.model.Account
import com.veem.model.Amount
import com.veem.model.Invoice

class InvoiceClientSpec extends Specification
{
    @Shared config = new JsonSlurper().parse(getClass().getResource( "testConfig.json" ))
    @Shared VeemContext veemContext = new VeemContext.VeemContextBuilder()
            .environment(SANDBOX)
            .build()
    @Shared VeemClient veemClient = veemContext.getClient(config.token)

    def "+ Send invoice"()
    {
        given:
        Invoice invoice = Invoice.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payer(Account.builder()
                    .email("test-${-> randomUUID()}@mailinator.com")
                    .firstName("Test")
                    .lastName("Veem API")
                    .countryCode(US)
                    .phone("2102102100")
                    .type(INCOMPLETE)
                    .build())
                .build()

        when:
        Invoice sentInvoice = veemClient.invoices().send(invoice)

        then:
        noExceptionThrown()
        sentInvoice.getStatus() == SENT
    }

    def "+ Get invoice"()
    {
        given:
        Invoice invoice = Invoice.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payer(Account.builder()
                .email("test-${-> randomUUID()}@mailinator.com")
                .firstName("Test")
                .lastName("Veem API")
                .countryCode(US)
                .phone("2102102100")
                .type(INCOMPLETE)
                .build())
                .build()
        Invoice sentInvoice = veemClient.invoices().send(invoice)

        when:
        Invoice fetchedInvoice = veemClient.invoices().get(sentInvoice.getId())

        then:
        noExceptionThrown()
        fetchedInvoice.getStatus() == SENT
    }

    def "+ Cancel invoice"()
    {
        given:
        Invoice invoice = Invoice.builder()
                .amount(new Amount(BigDecimal.valueOf(100), USD))
                .payer(Account.builder()
                .email("test-${-> randomUUID()}@mailinator.com")
                .firstName("Test")
                .lastName("Veem API")
                .countryCode(US)
                .phone("2102102100")
                .type(INCOMPLETE)
                .build())
                .build()
        Invoice sentInvoice = veemClient.invoices().send(invoice)

        when:
        Invoice cancelledInvoice = veemClient.invoices().cancel(sentInvoice.getId())

        then:
        noExceptionThrown()
        cancelledInvoice.getStatus() == CANCELLED
    }

    def cleanup()
    {
        sleep(1000)
    }
}
