package com.veem.model;

import com.veem.constants.ApprovalStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Approver
{
    private ApprovalStatus status;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
}
