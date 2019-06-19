package com.veem.client.converters;

import com.veem.client.responses.PaymentApprovalResponse;
import com.veem.model.PaymentApproval;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentApprovalConverter
{
    public static PaymentApproval convert(PaymentApprovalResponse approvalResponse)
    {
        return PaymentApproval.builder()
                .status(approvalResponse.getStatus())
                .approversRequired(approvalResponse.getApproverNumberRequired())
                .approversCompleted(approvalResponse.getApproverNumber())
                .approvers(ofNullable(approvalResponse.getUserApprovalResponseList()).orElseGet(Collections::emptyList)
                        .stream()
                        .map(UserApprovalConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }

}
