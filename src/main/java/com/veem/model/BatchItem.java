package com.veem.model;

import static com.veem.constants.BatchItemType.CONTACT;
import static com.veem.constants.BatchItemType.PAYMENT;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

import com.veem.constants.BatchItemType;
import com.veem.constants.BatchStatus;
import com.veem.exceptions.VeemSdkException;

@Builder
@Getter
public class BatchItem
{
    private final Long id;
    private final BatchStatus status;
    private final ErrorInfo errorInfo;
    private final Long paymentId;
    private final Long contactId;
    private final BatchItemType type;

    public Long getPaymentId() throws VeemSdkException
    {
        if (type != PAYMENT)
        {
            throw new VeemSdkException(String.format("Can't get paymentId from BatchItem with type %s", type));
        }
        return paymentId;
    }

    public Long getContactId() throws VeemSdkException
    {
        if (type != CONTACT)
        {
            throw new VeemSdkException(String.format("Can't get contactId from BatchItem with type %s", type));
        }
        return contactId;
    }

    @Builder
    @Getter
    public static class ErrorInfo
    {
        private final Long code;
        private final String error;
        private final String message;
        private final Date timestamp;
    }
}
