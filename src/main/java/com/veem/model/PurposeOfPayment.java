package com.veem.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurposeOfPayment
{
    private String purposeCode;
    private String description;
}