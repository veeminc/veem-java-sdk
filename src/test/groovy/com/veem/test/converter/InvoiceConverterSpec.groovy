package com.veem.test.converter

import com.neovisionaries.i18n.CountryCode
import com.neovisionaries.i18n.CurrencyCode
import com.veem.client.converters.InvoiceConverter
import com.veem.client.responses.AccountResponse
import com.veem.client.responses.InvoiceResponse
import com.veem.constants.InvoiceStatus
import com.veem.model.Account
import com.veem.model.Amount
import com.veem.model.Invoice
import spock.lang.Specification

class InvoiceConverterSpec extends Specification
{
    def "Converts from InvoiceResponse to Invoice"()
    {
        given:
        AccountResponse accountResponse = new AccountResponse(id: 777, firstName: "John", lastName: "smith",
                email:"jsmith@test.com", isoCountryCode: CountryCode.US)
        and:
        InvoiceResponse invoiceResponse = new InvoiceResponse(id: 1,
                status: InvoiceStatus.DRAFTED,
                ccEmails: Arrays.asList("test@test.com"),
                payer: accountResponse,
                claimLink:"http://claimLink.com")

        when:
        InvoiceConverter.convert(invoiceResponse)

        then:
        noExceptionThrown()
    }

    def "Converts from Invoice to InvoiceRequest"()
    {
        given:
        Account account = Account.builder()
            .firstName("John").lastName("Smith").email("jsmith@test.com")
            .countryCode(CountryCode.GB).isContact(true).build()
        and:
        Invoice invoice = Invoice.builder().payer(account).amount(Amount.builder()
        .currency(CurrencyCode.USD).number(BigDecimal.valueOf(5000)).build())
        .notes("notes").build()

        when:
        InvoiceConverter.convert(invoice)

        then:
        noExceptionThrown()
    }
}
