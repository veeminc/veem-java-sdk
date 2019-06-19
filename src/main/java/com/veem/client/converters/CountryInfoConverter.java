package com.veem.client.converters;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.stream.Collectors;

import com.veem.client.responses.CountryInfoResponse;
import com.veem.model.CountryInfo;

@NoArgsConstructor(access = PRIVATE)
public class CountryInfoConverter
{
    public static CountryInfo convert(final CountryInfoResponse countryInfoResponse)
    {
        return CountryInfo.builder()
                .bankFields(countryInfoResponse.getBankFields())
                .country(countryInfoResponse.getCountry())
                .invoiceAttachmentRequired(countryInfoResponse.isInvoiceAttachmentRequired())
                .purposeOfPaymentInfo(ofNullable(countryInfoResponse.getPurposeOfPaymentInfo())
                        .orElseGet(Collections::emptyList).stream()
                        .map(PurposeOfPaymentConverter::convert)
                        .collect(Collectors.toList()))
                .purposeOfPaymentRequired(countryInfoResponse.isPurposeOfPaymentRequired())
                .receivingCurrencies(countryInfoResponse.getReceivingCurrencies())
                .sendingCurrencies(countryInfoResponse.getSendingCurrencies())
                .build();
    }
}
