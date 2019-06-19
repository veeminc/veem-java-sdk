package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

import com.veem.model.PurposeOfPayment;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryInfoResponse
{
    private CountryCode country;
    private Iterable<CurrencyCode> sendingCurrencies;
    private Iterable<CurrencyCode> receivingCurrencies;
    private boolean purposeOfPaymentRequired = false;
    private boolean invoiceAttachmentRequired = false;
    private Collection<String> bankFields;
    private Collection<PurposeOfPaymentResponse> purposeOfPaymentInfo;
}