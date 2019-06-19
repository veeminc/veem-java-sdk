package com.veem.client.requests;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
public class AmountRequest
{
    private BigDecimal number;
    private CurrencyCode currency;
}
