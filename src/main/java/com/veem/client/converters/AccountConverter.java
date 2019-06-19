package com.veem.client.converters;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import com.veem.client.requests.AccountRequest;
import com.veem.client.responses.AccountResponse;
import com.veem.model.Account;

@NoArgsConstructor(access = PRIVATE)
public class AccountConverter
{
    public static Account convert(final AccountResponse accountResponse)
    {
        return Account.builder()
                .id(accountResponse.getId())
                .name(accountResponse.getName())
                .countryCode(accountResponse.getIsoCountryCode())
                .email(accountResponse.getEmail())
                .firstName(accountResponse.getFirstName())
                .lastName(accountResponse.getLastName())
                .isContact(accountResponse.getIsContact())
                .build();
    }

    public static AccountRequest convert(final Account account)
    {
        return AccountRequest.builder()
                .email(account.getEmail())
                .businessName(account.getBusinessName())
                .countryCode(account.getCountryCode())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .phone(account.getPhone())
                .phoneCountryCode(account.getPhoneCountryCode())
                .type(account.getType())
                .build();
    }
}
