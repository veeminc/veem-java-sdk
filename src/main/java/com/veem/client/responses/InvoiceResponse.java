package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.veem.constants.InvoiceStatus;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceResponse
{
    private Long id;
    private InvoiceStatus status;
    private ExchangeRateResponse exchangeRate;
    private String claimLink;
    private AccountResponse payer;
    private String clientId;
    private AmountResponse amount;
    private String notes;
    private String externalInvoiceRefId;
    private List<String> ccEmails;
    private String purposeOfPayment;
    private List<AttachmentResponse> attachments;
    private String exchangeRateQuoteId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private Date dueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private Date timeCreated;
}
