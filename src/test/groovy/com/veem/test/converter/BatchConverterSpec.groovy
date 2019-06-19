package com.veem.test.converter

import com.veem.client.converters.BatchConverter
import com.veem.client.responses.BatchItemResponse
import com.veem.client.responses.BatchItemResponse.ErrorInfoResponse
import com.veem.client.responses.BatchResponse
import com.veem.constants.BatchItemType
import com.veem.constants.BatchStatus
import com.veem.model.Batch
import com.veem.model.BatchItem
import spock.lang.Specification

class BatchConverterSpec extends Specification
{
    def "Converts from BatchResponse to Batch"()
    {
        given:
        BatchResponse batchResponse =  new BatchResponse(
                batchId: 123,
                status: BatchStatus.IN_PROGRESS,
                hasErrors: false,
                processedItems: 3,
                totalItems: 10,
                batchItems: getBatcItemResponseList()
        )

        when:
        Batch batch = BatchConverter.convert(BatchItemType.PAYMENT, batchResponse)

        then:
        noExceptionThrown()
        batch.status == BatchStatus.IN_PROGRESS
        batch.batchItems.size() == 10
    }

    def "Converts from BatchItemResponse to BatchItem"()
    {
        given:
        BatchItemResponse batchItemResponse = getBatchItemResponse(100)

        when:
        BatchItem batchItem = BatchConverter.convert(batchItemResponse, BatchItemType.PAYMENT)

        then:
        noExceptionThrown()
        batchItem != null
        batchItem.status == BatchStatus.IN_PROGRESS
        batchItem.type == BatchItemType.PAYMENT
        batchItem.errorInfo.code == 50000001
        batchItem.id == 100
    }

    def "Converts from ErrorInfoResponse to ErrorInfo"()
    {
        given:
        BatchItemResponse.ErrorInfoResponse errorInfoResponse = getErrorInfoResponse()
        when:
        BatchItem.ErrorInfo errorInfo = BatchConverter.convert(errorInfoResponse)

        then:
        noExceptionThrown()
        errorInfo.code == 50000001
        errorInfo.timestamp != null
    }

    private ErrorInfoResponse getErrorInfoResponse()
    {
        return new ErrorInfoResponse(
                code: 50000001,
                error: "Failed to get batchId",
                message: "A batch with ID 123 could not be found for account 777",
                timestamp: new Date())
    }

    private BatchItemResponse getBatchItemResponse(final Long batchItemId)
    {
        return new BatchItemResponse(
                batchItemId: batchItemId,
                status: BatchStatus.IN_PROGRESS,
                errorInfo: getErrorInfoResponse(),
                paymentId: 123,
                contactId: 777)
    }

    private List<BatchItemResponse> getBatcItemResponseList()
    {
        List<BatchItemResponse> batchItemResponseList = new ArrayList<>()
        for (int i = 1; i <= 10; i++)
        {
            batchItemResponseList.add(getBatchItemResponse(i));
        }
        return batchItemResponseList;
    }

}
