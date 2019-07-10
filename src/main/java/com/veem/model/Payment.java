package com.veem.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

import com.veem.constants.PaymentStatus;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Payment
{
    private Long id;
    private PaymentStatus status;
    private ExchangeRate exchangeRate;
    private Date timeCreated;
    private String claimLink;
    private PushPaymentInfo pushPaymentInfo;
    private PaymentApproval paymentApproval;
    private Long batchItemId;
    private Account payee;
    private Account payer;
    private String clientId;
    private Amount amount;
    private String notes;
    private String externalInvoiceRefId;
    private List<String> ccEmails;

    /**
     * A valid Purpose of Payment as returned by the {@}
     */
    private String purposeOfPayment;
    private List<Attachment> attachments;
    private String exchangeRateQuoteId;
}
