package com.veem.client;

import static com.veem.client.ResponseHandler.handleResponse;
import static com.veem.client.converters.ContactConverter.convert;
import static com.veem.constants.BatchItemType.CONTACT;
import static java.util.UUID.randomUUID;

import com.veem.client.converters.BatchConverter;
import com.veem.client.converters.ContactConverter;
import com.veem.client.converters.PageConverter;
import com.veem.exceptions.VeemException;
import com.veem.model.Batch;
import com.veem.model.Contact;
import com.veem.model.ContactListParameters;
import com.veem.model.Page;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

public final class ContactClient
{
    private final VeemClient veemClient;
    private final ContactApi contactApi;

    ContactClient(VeemClient veemClient)
    {
        this.veemClient = veemClient;
        this.contactApi = veemClient.context.contactApi;
    }

    /**
     * Gets a specific {@link Contact} from Veem, given an contact ID.
     *
     * @param contactId The ID of the {@link Contact} to fetch
     * @return The {@link Contact} with the requested ID, if it exists.
     */
    public Contact get(final long contactId) throws VeemException
    {
        return convert(handleResponse(contactApi.get(contactId, veemClient.authorization)::execute));
    }

    public Page<Contact> list(final ContactListParameters parameters) throws VeemException
    {
        return PageConverter.convert(handleResponse(
                contactApi.list(
                        parameters.getEmail(),
                        parameters.getFirstName(),
                        parameters.getLastName(),
                        parameters.getBusinessName(),
                        parameters.getBatchId(),
                        parameters.getBatchItemIds(),
                        parameters.getPageNumber(),
                        parameters.getPageNumber(),
                        veemClient.authorization)::execute),
                ContactConverter::convert);
    }

    public Contact create(final Contact contact) throws VeemException
    {
        val contactRequest = convert(contact);
        return convert(handleResponse(
                contactApi.create(contactRequest, randomUUID().toString(), veemClient.authorization)::execute));
    }

    public Batch createBatch(final List<Contact> contacts) throws VeemException
    {
        val contactRequests = contacts.stream()
                .map(ContactConverter::convert)
                .collect(Collectors.toList());
        return BatchConverter.convert(CONTACT, handleResponse(
                contactApi.createBatch(contactRequests, randomUUID().toString(), veemClient.authorization)::execute));
    }

    public Batch getBatch(final long batchId) throws VeemException
    {
        return BatchConverter.convert(CONTACT,
                handleResponse(contactApi.getBatch(batchId, veemClient.authorization)::execute));
    }
}
