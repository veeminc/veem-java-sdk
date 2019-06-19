package com.veem.client.converters;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.stream.Collectors;

import com.veem.client.requests.PaymentRequest;
import com.veem.client.responses.PaymentResponse;
import com.veem.model.Payment;

@NoArgsConstructor(access = PRIVATE)
public class PaymentConverter
{
    public static Payment convert(final PaymentResponse paymentResponse)
    {
        return Payment.builder()
                .id(paymentResponse.getId())
                .claimLink(paymentResponse.getClaimLink())
                .status(paymentResponse.getStatus())
                .batchItemId(paymentResponse.getBatchItemId())
                .amount(ofNullable(paymentResponse.getAmountResponse()).map(AmountConverter::convert).orElse(null))
                .payee(ofNullable(paymentResponse.getPayee()).map(AccountConverter::convert).orElse(null))
                .payer(ofNullable(paymentResponse.getPayer()).map(AccountConverter::convert).orElse(null))
                .attachments(ofNullable(paymentResponse.getAttachments()).orElseGet(Collections::emptyList).stream()
                        .map(AttachmentConverter::convert)
                        .collect(Collectors.toList()))
                .ccEmails(paymentResponse.getCcEmails())
                .pushPaymentInfo(ofNullable(paymentResponse.getPushPaymentInfoResponse()).map(PushPaymentInfoConverter::convert).orElse(null))
                .paymentApproval(ofNullable(paymentResponse.getPaymentApprovalResponse()).map(PaymentApprovalConverter::convert).orElse(null))
                .build();
    }

    public static PaymentRequest convert(final Payment payment)
    {
        return PaymentRequest.builder()
                .amount(AmountConverter.convert(payment.getAmount()))
                .attachments(ofNullable(payment.getAttachments()).orElseGet(Collections::emptyList).stream()
                        .map(AttachmentConverter::convert)
                        .collect(Collectors.toList()))
                .batchItemId(payment.getBatchItemId())
                .ccEmails(payment.getCcEmails())
                .exchangeRateQuoteId(payment.getExchangeRateQuoteId())
                .externalInvoiceRefId(payment.getExternalInvoiceRefId())
                .notes(payment.getNotes())
                .payee(AccountConverter.convert(payment.getPayee()))
                .purposeOfPayment(payment.getPurposeOfPayment())
                .build();
    }
}
