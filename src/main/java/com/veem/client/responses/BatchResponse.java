package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.veem.constants.BatchStatus;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchResponse
{
    private Long batchId;
    private BatchStatus status;
    private Boolean hasErrors;
    private Integer processedItems;
    private Integer totalItems;
    private List<BatchItemResponse> batchItems;
}
