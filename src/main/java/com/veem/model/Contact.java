package com.veem.model;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.veem.constants.AccountType;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Contact
{
    private Long id;
    private Long externalId;
    private Long contactAccountId;
    private String email;
    private String firstName;
    private String lastName;
    private String businessName;
    private CountryCode countryCode;
    private String dialCode;
    private String phoneNumber;
    private Long batchItemId;
    private AccountType type;
    private BankAccount bankAccount;
    private Address address;
}
