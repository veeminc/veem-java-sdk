package com.veem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class ContactListParameters
{
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String businessName;
    private final Long batchId;
    private final List<Long> batchItemIds;
    private final Integer pageNumber;
    private final Integer pageSize;
}
