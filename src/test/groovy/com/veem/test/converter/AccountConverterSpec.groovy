package com.veem.test.converter

import com.neovisionaries.i18n.CountryCode
import com.veem.client.converters.AccountConverter
import com.veem.client.requests.AccountRequest
import com.veem.client.responses.AccountResponse
import com.veem.constants.AccountType
import com.veem.model.Account
import spock.lang.Specification


class AccountConverterSpec extends Specification
{

    static final String EMAIL = "jdoe@testclient.com"
    static final String FIRSTNAME = "John"
    static final String LASTNAME = "Doe"
    static final String NAME = "John Doe"

    def  "Converts from AccountResponse to Account"()
    {
        given:
        AccountResponse accountResponse = new AccountResponse(id:1,
                firstName: FIRSTNAME,lastName: LASTNAME, name : NAME,
                email: EMAIL, isoCountryCode: CountryCode.US)

        when:
        Account account = AccountConverter.convert(accountResponse)

        then:
        account != null
        account.firstName == FIRSTNAME
        account.lastName == LASTNAME
        account.email == EMAIL
    }

    def "Converts from Account to AccountRequest"()
    {
        given:
        Account account = new Account.AccountBuilder()
            .type(AccountType.BUSINESS)
            .lastName(LASTNAME)
            .firstName(FIRSTNAME)
            .email(EMAIL)
            .countryCode(CountryCode.AD)
            .phone("2453211")
            .phoneCountryCode("91")
            .id(1)
            .build()

        when:
        AccountRequest accountRequest = AccountConverter.convert(account)

        then:
        accountRequest.email == EMAIL
        accountRequest.lastName == LASTNAME
    }
}
