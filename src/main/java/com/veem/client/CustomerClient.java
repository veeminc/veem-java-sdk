package com.veem.client;

import static com.veem.client.ResponseHandler.handleResponse;

import com.veem.client.converters.CustomerConverter;
import com.veem.client.converters.PageConverter;
import com.veem.exceptions.VeemException;
import com.veem.model.Account;
import com.veem.model.AccountListParameters;
import com.veem.model.Page;

public final class CustomerClient
{
    private final VeemClient veemClient;
    private final CustomerApi customerApi;


    public CustomerClient(VeemClient veemClient) {
        this.veemClient = veemClient;
        this.customerApi = veemClient.context.customerApi;
    }

    public Page<Account> list(final AccountListParameters accountListParameters) throws VeemException
    {
        return PageConverter.convert(handleResponse(
                customerApi.list(accountListParameters.getEmail(), veemClient.authorization)::execute),
                CustomerConverter::convert);
    }
}
