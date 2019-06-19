package com.veem.client.requests;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;

import com.veem.constants.AccountType;

@Builder
@Getter
public class ContactRequest
{
    private final String email;
    private final AccountType type;
    private final String firstName;
    private final String lastName;
    private final String businessName;
    private final CountryCode isoCountryCode;
    private final String phoneDialCode;
    private final String phoneNumber;
    private final Long externalBusinessId;
    private final AddressRequest businessAddress;
    private final BankAccountRequest bankAccount;
    private final Long batchItemId;
}
