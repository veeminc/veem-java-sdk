package com.veem.client.requests;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

import com.veem.constants.InvoiceStatus;

@Builder
@Getter
@JsonInclude(NON_NULL)
public class InvoiceRequest
{
    private Long id;
    private InvoiceStatus status;
    private Date timeCreated;
    private String claimLink;
    private AccountRequest payer;
    private String clientId;
    private AmountRequest amount;
    private String notes;
    private String externalInvoiceRefId;
    private List<String> ccEmails;
    private String purposeOfPayment;
    private List<AttachmentRequest> attachments;
    private String exchangeRateQuoteId;
    private Date dueDate;
}
