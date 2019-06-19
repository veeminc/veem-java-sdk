package com.veem.client.converters;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import com.veem.client.requests.AddressRequest;
import com.veem.client.requests.BankAccountRequest;
import com.veem.client.requests.ContactRequest;
import com.veem.client.responses.ContactResponse;
import com.veem.model.Address;
import com.veem.model.BankAccount;
import com.veem.model.Contact;

@NoArgsConstructor(access = PRIVATE)
public class ContactConverter
{
    public static Contact convert(final ContactResponse contactResponse)
    {
        return Contact.builder()
                .id(contactResponse.getId())
                .contactAccountId(contactResponse.getContactAccountId())
                .email(contactResponse.getEmail())
                .firstName(contactResponse.getFirstName())
                .lastName(contactResponse.getLastName())
                .businessName(contactResponse.getBusinessName())
                .countryCode(contactResponse.getIsoCountryCode())
                .dialCode(contactResponse.getDialCode())
                .phoneNumber(contactResponse.getPhoneNumber())
                .batchItemId(contactResponse.getBatchItemId())
                .build();
    }

    public static ContactRequest convert(final Contact contact)
    {
        return ContactRequest.builder()
                .businessName(contact.getBusinessName())
                .email(contact.getEmail())
                .externalBusinessId(contact.getExternalId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .isoCountryCode(contact.getCountryCode())
                .phoneDialCode(contact.getDialCode())
                .phoneNumber(contact.getPhoneNumber())
                .type(contact.getType())
                .batchItemId(contact.getBatchItemId())
                .businessAddress(ofNullable(contact.getAddress()).map(ContactConverter::convert).orElse(null))
                .bankAccount(ofNullable(contact.getBankAccount()).map(ContactConverter::convert).orElse(null))
                .build();
    }

    private static AddressRequest convert(final Address address)
    {
        return AddressRequest.builder()
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .stateProvince(address.getStateProvince())
                .build();
    }

    private static BankAccountRequest convert(final BankAccount bankAccount)
    {
        return BankAccountRequest.builder()
                .bankAccountNumber(bankAccount.getBankAccountNumber())
                .bankCnaps(bankAccount.getBankCnaps())
                .bankCode(bankAccount.getBankCode())
                .bankIfscBranchCode(bankAccount.getBankIfscBranchCode())
                .bankInstitutionNumber(bankAccount.getBankInstitutionNumber())
                .bankName(bankAccount.getBankName())
                .beneficiaryName(bankAccount.getBeneficiaryName())
                .branchCode(bankAccount.getBranchCode())
                .bsbBankCode(bankAccount.getBsbBankCode())
                .clabe(bankAccount.getClabe())
                .currencyCode(bankAccount.getCurrencyCode())
                .iban(bankAccount.getIban())
                .isoCountryCode(bankAccount.getIsoCountryCode())
                .routingNumber(bankAccount.getRoutingNumber())
                .sortCode(bankAccount.getSortCode())
                .swiftBic(bankAccount.getSwiftBic())
                .transitCode(bankAccount.getTransitCode())
                .bankAddress(ofNullable(bankAccount.getBankAddress()).map(ContactConverter::convert).orElse(null))
                .build();
    }
}
