package com.veem.client.responses;

import com.veem.constants.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserApprovalResponse
{
    private final ApprovalStatus approvalStatus;
    private final String firstName;
    private final String lastName;
    private final String middleName;
}
