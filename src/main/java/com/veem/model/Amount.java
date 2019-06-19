package com.veem.model;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
public class Amount
{
    private BigDecimal number;
    private CurrencyCode currency;
}
