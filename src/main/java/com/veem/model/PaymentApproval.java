package com.veem.model;

import com.veem.constants.ApprovalStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PaymentApproval
{
    private Long approversCompleted;
    private ApprovalStatus status;
    private Long approversRequired;
    private List<Approver> approvers;
}
