package com.veem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Address
{
    private final String line1;
    private final String line2;
    private final String city;
    private final String stateProvince;
    private final String postalCode;
}
