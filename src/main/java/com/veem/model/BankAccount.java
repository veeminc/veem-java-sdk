package com.veem.model;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * This is a public API request structure. Changing it requires
 * careful consideration of backwards compatibility.
 */
@Builder
@Getter
@ToString
public class BankAccount
{
    private final String routingNumber;
    private final String bankName;
    private final String bankAccountNumber;
    private final CurrencyCode currencyCode;
    private final CountryCode isoCountryCode;
    private final String iban;
    private final String swiftBic;
    private final String beneficiaryName;
    private final String bsbBankCode;
    private final String branchCode;
    private final String transitCode;
    private final String bankInstitutionNumber;
    private final String bankIfscBranchCode;
    private final String sortCode;
    private final String bankCode;
    private final String clabe;
    private final String bankCnaps;
    private final Address bankAddress;
}
