package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.veem.constants.BatchItemType;
import com.veem.constants.BatchStatus;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchItemResponse
{
    private Long batchItemId;
    private BatchStatus status;
    private ErrorInfoResponse errorInfo;
    private Long paymentId;
    private Long contactId;

    @Getter
    @Setter
    public static class ErrorInfoResponse
    {
        private Long code;
        private String error;
        private String message;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
        private Date timestamp;
    }
}
