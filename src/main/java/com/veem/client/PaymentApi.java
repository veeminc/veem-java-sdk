package com.veem.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

import com.veem.client.responses.BatchResponse;
import com.veem.client.responses.PageResponse;
import com.veem.client.requests.PaymentRequest;
import com.veem.client.responses.PaymentResponse;
import com.veem.constants.PaymentStatus;

/**
 * API endpoints.
 */
interface PaymentApi
{
    String BASE = "/veem/v1.1/payments";

    @POST(BASE)
    Call<PaymentResponse> create(
            @Body PaymentRequest paymentRequest,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @POST(BASE + "/{paymentId}/approve")
    Call<PaymentResponse> send(
            @Path("paymentId") Long paymentId,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @POST(BASE + "/{paymentId}/cancel")
    Call<PaymentResponse> cancel(
            @Path("paymentId") Long paymentId,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @GET(BASE + "/{paymentId}")
    Call<PaymentResponse> get(
            @Path("paymentId") Long paymentId,
            @Header("Authorization") String authorization);

    @GET(BASE)
    Call<PageResponse<PaymentResponse>> list(
            @Query("direction") String direction,
            @Query("paymentIds") List<Long> paymentIds,
            @Query("status") Set<PaymentStatus> status,
            @Query("sort") List<String> sortParameters,
            @Query("pageNumber") Integer pageNumber,
            @Query("pageSize") Integer pageSize,
            @Header("Authorization") String authorization);

    @GET(BASE + "/simple")
    Call<PageResponse<PaymentResponse>> listSimple(
            @Query("direction") String direction,
            @Header("Authorization") String authorization);

    @POST(BASE + "/batch?includeItems=true")
    Call<BatchResponse> createBatch(
            @Body List<PaymentRequest> paymentRequests,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @GET(BASE + "/batch/{batchId}?includeItems=true")
    Call<BatchResponse> getBatch(
            @Path("batchId") Long batchId,
            @Header("Authorization") String authorization);
}
