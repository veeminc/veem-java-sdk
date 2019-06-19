package com.veem.model;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Builder
@Getter
public class CountryInfo
{
    private CountryCode country;
    private Iterable<CurrencyCode> sendingCurrencies;
    private Iterable<CurrencyCode> receivingCurrencies;
    private boolean purposeOfPaymentRequired;
    private boolean invoiceAttachmentRequired;
    private Collection<String> bankFields;
    private Collection<PurposeOfPayment> purposeOfPaymentInfo;
}