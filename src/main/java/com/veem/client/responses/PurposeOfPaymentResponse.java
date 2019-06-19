package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurposeOfPaymentResponse
{
    private String purposeCode;
    private String description;
}