package com.veem.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.veem.client.requests.InvoiceRequest;
import com.veem.client.responses.InvoiceResponse;

/**
 * API endpoints.
 */
interface InvoiceApi
{
    String BASE = "/veem/v1.1/invoices";

    @POST(BASE)
    Call<InvoiceResponse> create(
            @Body InvoiceRequest invoiceRequest,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @POST(BASE + "/{invoiceId}/approve")
    Call<InvoiceResponse> send(
            @Path("invoiceId") Long invoiceId,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @POST(BASE + "/{invoiceId}/cancel")
    Call<InvoiceResponse> cancel(
            @Path("invoiceId") Long invoiceId,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @GET(BASE + "/{invoiceId}")
    Call<InvoiceResponse> get(
            @Path("invoiceId") Long invoiceId,
            @Header("Authorization") String authorization);
}
