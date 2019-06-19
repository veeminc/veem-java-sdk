package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.veem.constants.PaymentStatus;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponse
{
    private Long id;
    private String claimLink;
    private String externalInvoiceRefId;
    private AccountResponse payee;
    private AccountResponse payer;
    private PaymentStatus status;
    private AmountResponse amountResponse;
    private ExchangeRateResponse exchangeRate;
    private List<AttachmentResponse> attachments;
    private Long batchItemId;
    private List<String> ccEmails;
    private String exchangeRateQuoteId;
    private String purposeOfPayment;
    private PushPaymentInfoResponse pushPaymentInfoResponse;
    private PaymentApprovalResponse paymentApprovalResponse;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM'T'hh:mm:ss.SSS'Z'")
    private Date timeCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM'T'hh:mm:ss.SSS'Z'")
    private Date timeUpdated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM'T'hh:mm:ss.SSS'Z'")
    private Date timeSent;
}
