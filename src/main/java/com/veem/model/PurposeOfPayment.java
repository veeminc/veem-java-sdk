package com.veem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PurposeOfPayment
{
    private String purposeCode;
    private String description;
}