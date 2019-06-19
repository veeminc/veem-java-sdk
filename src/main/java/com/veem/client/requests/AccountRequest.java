package com.veem.client.requests;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;

import com.veem.constants.AccountType;

@Builder
@Getter
public class AccountRequest
{
    private final AccountType type;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String businessName;
    private final CountryCode countryCode;
    private final String phoneCountryCode;
    private final String phone;
}
