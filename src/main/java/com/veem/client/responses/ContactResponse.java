package com.veem.client.responses;

import com.neovisionaries.i18n.CountryCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponse
{
    private Long id;
    private Long contactAccountId;
    private String email;
    private String firstName;
    private String lastName;
    private String businessName;
    private CountryCode isoCountryCode;
    private String dialCode;
    private String phoneNumber;
    private Long batchItemId;
}
