package com.veem.client;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.Collection;

import com.veem.client.responses.CountryInfoResponse;

/**
 * API endpoints.
 */
interface MetadataApi
{
    String BASE = "/veem/public/v1.1/country-currency-map";

    @GET(BASE)
    Call<Collection<CountryInfoResponse>> getCountryCurrencyMap();
}
