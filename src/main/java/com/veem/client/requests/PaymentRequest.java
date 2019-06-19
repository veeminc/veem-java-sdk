package com.veem.client.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentRequest
{
    private final Long batchItemId;
    private final String exchangeRateQuoteId;
    private final AccountRequest payee;
    private final AmountRequest amount;
    private final String notes;
    private final String externalInvoiceRefId;
    private final Iterable<String> ccEmails;
    private final String purposeOfPayment;
    private final Iterable<AttachmentRequest> attachments;
    private Boolean approveAutomatically;
}
