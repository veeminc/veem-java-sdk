package com.veem.client;

import static com.veem.client.ResponseHandler.handleResponse;
import static com.veem.client.converters.ExchangeRateConverter.convert;
import static java.util.UUID.randomUUID;

import com.veem.client.requests.ExchangeRateRequest;
import com.veem.exceptions.VeemException;
import com.veem.model.ExchangeRate;

public final class ExchangeRateClient
{
    private final VeemClient veemClient;
    private final ExchangeRateApi exchangeRateApi;

    ExchangeRateClient(VeemClient veemClient)
    {
        this.veemClient = veemClient;
        this.exchangeRateApi = veemClient.context.exchangeRateApi;
    }

    /**
     * Gets a specific {@link ExchangeRate} from Veem, given an invoice ID.
     *
     * @param exchangeRate The ID of the {@link ExchangeRate} to fetch
     * @return The {@link ExchangeRate} with the requested ID, if it exists.
     */
    public ExchangeRate generate(final ExchangeRate exchangeRate) throws VeemException
    {
        final ExchangeRateRequest exchangeRateRequest = convert(exchangeRate);
        return convert(handleResponse(exchangeRateApi.generate(
                exchangeRateRequest, randomUUID().toString(), veemClient.authorization)::execute));
    }
}
