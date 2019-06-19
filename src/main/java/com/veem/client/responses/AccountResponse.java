package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neovisionaries.i18n.CountryCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResponse
{
    private Long id;
    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private CountryCode isoCountryCode;
    private Boolean isContact = false;
}
