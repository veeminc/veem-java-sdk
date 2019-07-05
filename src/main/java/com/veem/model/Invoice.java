package com.veem.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

import com.veem.constants.InvoiceStatus;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Invoice
{
    private Long id;
    private InvoiceStatus status;
    private ExchangeRate exchangeRate;
    private Date timeCreated;
    private String claimLink;
    private Account payer;
    private String clientId;
    private Amount amount;
    private String notes;
    private String externalInvoiceRefId;
    private List<String> ccEmails;
    private String purposeOfPayment;
    private List<Attachment> attachments;
    private String exchangeRateQuoteId;
    private Date dueDate;
}
