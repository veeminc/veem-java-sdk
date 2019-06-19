package com.veem.client.converters;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.stream.Collectors;

import com.veem.client.requests.InvoiceRequest;
import com.veem.client.responses.InvoiceResponse;
import com.veem.model.Invoice;

@NoArgsConstructor(access = PRIVATE)
public class InvoiceConverter
{
    public static Invoice convert(final InvoiceResponse invoiceResponse)
    {
        return Invoice.builder()
                .id(invoiceResponse.getId())
                .payer(AccountConverter.convert(invoiceResponse.getPayer()))
                .status(invoiceResponse.getStatus())
                .amount(ofNullable(invoiceResponse.getAmount()).map(AmountConverter::convert).orElse(null))
                .ccEmails(ofNullable(invoiceResponse.getCcEmails()).orElseGet(Collections::emptyList))
                .attachments(ofNullable(invoiceResponse.getAttachments()).orElseGet(Collections::emptyList)
                        .stream().map(AttachmentConverter::convert).collect(Collectors.toList()))
                .claimLink(invoiceResponse.getClaimLink())
                .dueDate(invoiceResponse.getDueDate())
                .build();
    }

    public static InvoiceRequest convert(final Invoice invoice)
    {
        return InvoiceRequest.builder()
                .payer(AccountConverter.convert(invoice.getPayer()))
                .amount(AmountConverter.convert(invoice.getAmount()))
                .ccEmails(invoice.getCcEmails())
                .dueDate(invoice.getDueDate())
                .purposeOfPayment(invoice.getPurposeOfPayment())
                .externalInvoiceRefId(invoice.getExternalInvoiceRefId())
                .attachments(ofNullable(invoice.getAttachments()).orElseGet(Collections::emptyList)
                        .stream().map(AttachmentConverter::convert).collect(Collectors.toList()))
                .notes(invoice.getNotes())
                .build();
    }
}
