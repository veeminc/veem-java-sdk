package com.veem.client.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.veem.constants.ApprovalStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentApprovalResponse
{
    private ApprovalStatus status;
    private Long approverNumber;
    private Long approverNumberRequired;
    private List<UserApprovalResponse> userApprovalResponseList;
}
