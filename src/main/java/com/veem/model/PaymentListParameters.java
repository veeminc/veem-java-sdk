package com.veem.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.veem.constants.Direction;
import com.veem.constants.PaymentSortField;
import com.veem.constants.PaymentStatus;
import com.veem.constants.SortOrder;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PaymentListParameters
{
    private final Direction direction;
    private final List<Long> paymentIds;
    private final Long batchId;
    private final Set<PaymentStatus> status;
    private final Map<PaymentSortField, SortOrder> sortParameters;
    private final Integer pageNumber;
    private final Integer pageSize;
}
