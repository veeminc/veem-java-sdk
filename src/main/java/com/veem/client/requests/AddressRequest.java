package com.veem.client.requests;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressRequest
{
    private final String line1;
    private final String line2;
    private final String city;
    private final String stateProvince;
    private final String postalCode;
}
