package com.veem.model;

import lombok.*;

import java.util.List;

import com.veem.constants.BatchStatus;

@Builder
@Getter
@ToString
public class Batch
{
    private final Long id;
    private final BatchStatus status;
    private final Boolean hasErrors;
    private final Integer processedItems;
    private final Integer totalItems;
    private List<BatchItem> batchItems;
}
