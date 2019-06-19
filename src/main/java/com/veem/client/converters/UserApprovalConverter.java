package com.veem.client.converters;

import com.veem.model.Approver;
import com.veem.client.responses.UserApprovalResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserApprovalConverter
{
    public static Approver convert(UserApprovalResponse userApprovalResponse)
    {
        return Approver.builder()
                .status(userApprovalResponse.getApprovalStatus())
                .firstName(userApprovalResponse.getFirstName())
                .lastName(userApprovalResponse.getLastName())
                .middleName(userApprovalResponse.getMiddleName())
                .build();
    }
}
