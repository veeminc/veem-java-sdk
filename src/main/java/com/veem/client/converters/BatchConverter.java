package com.veem.client.converters;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collections;

import com.veem.client.responses.BatchItemResponse;
import com.veem.client.responses.BatchItemResponse.ErrorInfoResponse;
import com.veem.client.responses.BatchResponse;
import com.veem.constants.BatchItemType;
import com.veem.model.Batch;
import com.veem.model.BatchItem;
import com.veem.model.BatchItem.ErrorInfo;

@NoArgsConstructor(access = PRIVATE)
public class BatchConverter
{
    public static Batch convert(final BatchItemType type, @NonNull final BatchResponse batchResponse)
    {
        return Batch.builder()
                .id(batchResponse.getBatchId())
                .status(batchResponse.getStatus())
                .batchItems(ofNullable(batchResponse.getBatchItems())
                        .orElseGet(Collections::emptyList).stream()
                        .map(item -> convert(item, type))
                        .collect(toList()))
                .hasErrors(batchResponse.getHasErrors())
                .processedItems(batchResponse.getProcessedItems())
                .totalItems(batchResponse.getTotalItems())
                .build();
    }

    private static BatchItem convert(@NonNull final BatchItemResponse batchItemResponse, final BatchItemType type)
    {
        return BatchItem.builder()
                .id(batchItemResponse.getBatchItemId())
                .status(batchItemResponse.getStatus())
                .contactId(batchItemResponse.getContactId())
                .paymentId(batchItemResponse.getPaymentId())
                .errorInfo(ofNullable(batchItemResponse.getErrorInfo())
                        .map(BatchConverter::convert)
                        .orElse(null))
                .type(type)
                .build();
    }

    private static ErrorInfo convert(@NonNull final ErrorInfoResponse errorInfoResponse)
    {
        return ErrorInfo.builder()
                .code(errorInfoResponse.getCode())
                .error(errorInfoResponse.getError())
                .message(errorInfoResponse.getMessage())
                .timestamp(errorInfoResponse.getTimestamp())
                .build();
    }
}
