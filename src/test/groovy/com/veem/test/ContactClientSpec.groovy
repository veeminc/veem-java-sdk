package com.veem.test

import static com.veem.client.VeemContext.Environment.SANDBOX

import com.neovisionaries.i18n.CountryCode
import com.neovisionaries.i18n.CurrencyCode
import com.veem.model.Address
import com.veem.model.BankAccount
import com.veem.model.Batch

import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import com.veem.client.VeemClient
import com.veem.client.VeemContext
import com.veem.model.Contact
import com.veem.model.ContactListParameters
import com.veem.model.Page

class ContactClientSpec extends Specification
{

    @Shared
    config = new JsonSlurper().parse(getClass().getResource("testConfig.json"))
    @Shared
    VeemContext veemContext = new VeemContext.VeemContextBuilder()
            .environment(SANDBOX)
            .build()
    @Shared
    VeemClient veemClient = veemContext.getClient(config.token)

    def "+ List contacts"() {
        given:
        ContactListParameters parameters = ContactListParameters.builder().build()

        when:
        Page<Contact> contacts = veemClient.contacts().list(parameters)

        then:
        noExceptionThrown()
    }

    def "Create contact"() {
        given:
        Contact contact = getContact()

        when:
        Contact createdContact = veemClient.contacts().create(contact)

        then:
        noExceptionThrown()
        createdContact.email == contact.email
        createdContact.firstName == contact.firstName
    }


    def "Create batch of contacts"() {
        given: "Constructs a list of contacts"
        List<Contact> contactList = buildContactsList()

        when:
        Batch batch = veemClient.contacts().createBatch(contactList)

        then:
        noExceptionThrown()
        batch != null
    }

    def cleanup()
    {
        sleep(1000)
    }

    private String getUniqueEmail()
    {
        int lowerBound = 97;
        int upperBound = 122;
        int limit = 22;

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder()
        for (int i = 0; i < limit; i++)
        {
            int nextChar = lowerBound + (int) (random.nextFloat() * (upperBound - lowerBound + 1))
            stringBuilder.append((char) nextChar);
        }
        return stringBuilder.toString() + "@testclient.com"
    }

    private Contact getContact()
    {
        Address bankAddress = Address.builder()
                .line1("Line 1 ")
                .line2("Line 2")
                .city("New York")
                .stateProvince("NY")
                .postalCode("10005")
                .build()

        BankAccount bankAccount = BankAccount.builder()
                .isoCountryCode(CountryCode.US)
                .currencyCode(CurrencyCode.USD)
                .bankName("BMA")
                .bankAccountNumber("123456")
                .routingNumber("111000025")
                .bankAddress(bankAddress)
                .build()

        Contact contact = Contact.builder()
                .email(uniqueEmail)
                .firstName("firstname")
                .lastName("lastname")
                .countryCode(CountryCode.US)
                .phoneNumber("19059990111")
                .bankAccount(bankAccount)
                .build()

        return contact
    }

    private List<Contact> buildContactsList()
    {
        int batchItem = 0;
        List<Contact> contactList = new ArrayList<>()
        for (int i = 0; i < 10; i++)
        {
            Contact contact = Contact.builder()
                    .batchItemId(batchItem++)
                    .email(uniqueEmail)
                    .firstName("batch")
                    .lastName("user")
                    .countryCode(CountryCode.US)
                    .phoneNumber("19059990111")
                    .build()

            contactList.add(contact)
        }
        return contactList
    }
}
