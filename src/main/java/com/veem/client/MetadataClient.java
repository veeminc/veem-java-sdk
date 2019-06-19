package com.veem.client;

import static com.veem.client.ResponseHandler.handleResponse;

import java.util.Collection;
import java.util.stream.Collectors;

import com.veem.client.converters.CountryInfoConverter;
import com.veem.exceptions.VeemException;
import com.veem.model.CountryInfo;

final class MetadataClient
{
    private final MetadataApi metadataApi;

    MetadataClient(VeemClient veemClient)
    {
        this.metadataApi = veemClient.context.metadataApi;
    }

    /**
     *
     * @return
     */
    public Collection<CountryInfo> getCountryCurrencyMap() throws VeemException
    {
        return handleResponse(metadataApi.getCountryCurrencyMap()::execute).stream()
                .map(CountryInfoConverter::convert)
                .collect(Collectors.toList());
    }
}
