package com.veem.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import com.veem.client.requests.ExchangeRateRequest;
import com.veem.client.responses.ExchangeRateResponse;

/**
 * API endpoints.
 */
interface ExchangeRateApi
{
    String BASE = "/veem/v1.1/exchangerates";

    @POST(BASE + "/quotes")
    Call<ExchangeRateResponse> generate(
            @Body ExchangeRateRequest exchangeRateRequest,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);
}
