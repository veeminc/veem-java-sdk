package com.veem.model;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.veem.constants.AccountType;

@Builder
@Getter
public class Account
{
    private AccountType type;
    private String email;
    private String firstName;
    private String lastName;
    private String businessName;
    private CountryCode countryCode;
    private String phoneCountryCode;
    private String phone;
    private Long id;
    private String name;
    private Boolean isContact;
    private AccountType accountType;

}
