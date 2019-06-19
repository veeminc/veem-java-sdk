package com.veem.client;

import static com.veem.client.ResponseHandler.handleResponse;
import static com.veem.client.converters.InvoiceConverter.convert;
import static java.util.UUID.randomUUID;

import com.veem.client.requests.InvoiceRequest;
import com.veem.exceptions.VeemException;
import com.veem.model.Invoice;

public final class InvoiceClient
{
    private final VeemClient veemClient;
    private final InvoiceApi invoiceApi;

    InvoiceClient(VeemClient veemClient)
    {
        this.veemClient = veemClient;
        this.invoiceApi = veemClient.context.invoiceApi;
    }

    /**
     * Gets a specific {@link Invoice} from Veem, given an invoice ID.
     *
     * @param invoice The ID of the {@link Invoice} to fetch
     * @return The {@link Invoice} with the requested ID, if it exists.
     */
    public Invoice draft(final Invoice invoice) throws VeemException
    {
        final InvoiceRequest invoiceRequest = convert(invoice);
        return convert(handleResponse(
                invoiceApi.create(invoiceRequest, randomUUID().toString(), veemClient.authorization)::execute));
    }

    /**
     * Gets a specific {@link Invoice} from Veem, given an invoice ID.
     *
     * @param invoice The ID of the {@link Invoice} to fetch
     * @return The {@link Invoice} with the requested ID, if it exists.
     */
    public Invoice send(final Invoice invoice) throws VeemException
    {
        final InvoiceRequest invoiceRequest = convert(invoice);
        return convert(handleResponse(
                invoiceApi.create(invoiceRequest, randomUUID().toString(), veemClient.authorization)::execute));
    }

    /**
     * Gets a specific {@link Invoice} from Veem, given an invoice ID.
     *
     * @param invoiceId The ID of the {@link Invoice} to fetch
     * @return The {@link Invoice} with the requested ID, if it exists.
     */
    public Invoice get(final long invoiceId) throws VeemException
    {
        return convert(handleResponse(invoiceApi.get(invoiceId, veemClient.authorization)::execute));
    }

    /**
     * Gets a specific {@link Invoice} from Veem, given an invoice ID.
     *
     * @param invoiceId The ID of the {@link Invoice} to send
     * @return The {@link Invoice} with the requested ID, if it exists.
     */
    public Invoice send(final long invoiceId) throws VeemException
    {
        return convert(handleResponse(
                invoiceApi.send(invoiceId, randomUUID().toString(), veemClient.authorization)::execute));
    }

    /**
     * Gets a specific {@link Invoice} from Veem, given an invoice ID.
     *
     * @param invoiceId The ID of the {@link Invoice} to cancel
     * @return The {@link Invoice} with the requested ID, if it exists.
     */
    public Invoice cancel(final long invoiceId) throws VeemException
    {
        return convert(handleResponse(
                invoiceApi.cancel(invoiceId, randomUUID().toString(), veemClient.authorization)::execute));
    }
}
