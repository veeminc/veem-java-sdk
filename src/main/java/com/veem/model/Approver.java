package com.veem.model;

import com.veem.constants.ApprovalStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Approver
{
    private ApprovalStatus status;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
}
