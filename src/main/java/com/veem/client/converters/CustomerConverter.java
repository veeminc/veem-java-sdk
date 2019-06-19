package com.veem.client.converters;

import com.veem.client.responses.AccountResponse;
import com.veem.model.Account;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CustomerConverter
{
    public static Account convert(AccountResponse accountResponse)
    {
        return Account.builder()
                .name(accountResponse.getName())
                .firstName(accountResponse.getFirstName())
                .lastName(accountResponse.getLastName())
                .id(accountResponse.getId())
                .isContact(accountResponse.getIsContact())
                .email(accountResponse.getEmail())
                .countryCode(accountResponse.getIsoCountryCode())
                .build();
    }

}
