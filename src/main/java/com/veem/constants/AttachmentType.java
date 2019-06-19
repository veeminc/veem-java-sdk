package com.veem.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttachmentType
{
    EXTERNAL_INVOICE("ExternalInvoice"),
    PROOF_OF_PAYMENT("ProofOfPayment");

    @JsonValue
    private String jsonValue;
}
