package com.veem.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

import com.veem.client.requests.ContactRequest;
import com.veem.client.responses.BatchResponse;
import com.veem.client.responses.ContactResponse;
import com.veem.client.responses.PageResponse;

/**
 * API endpoints.
 */
interface ContactApi
{
    String BASE = "/veem/v1.1/contacts";

    @POST(BASE)
    Call<ContactResponse> create(
            @Body ContactRequest contactRequest,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @GET(BASE + "/{contactId}")
    Call<ContactResponse> get(
            @Path("contactId") Long contactId,
            @Header("Authorization") String authorization);

    @GET(BASE)
    Call<PageResponse<ContactResponse>> list(
            @Query("email") String email,
            @Query("firstName") String firstName,
            @Query("lastName") String lastName,
            @Query("businessName") String businessName,
            @Query("batchId") Long batchId,
            @Query("batchItemIds") List<Long> batchItemIds,
            @Query("pageNumber") Integer pageNumber,
            @Query("pageSize") Integer pageSize,
            @Header("Authorization") String authorization);

    @POST(BASE + "/batch")
    Call<BatchResponse> createBatch(
            @Body List<ContactRequest> contactRequests,
            @Header("X-REQUEST-ID") String requestId,
            @Header("Authorization") String authorization);

    @GET(BASE + "/batch/{batchId}")
    Call<BatchResponse> getBatch(
            @Path("batchId") long batchId,
            @Header("Authorization") String authorization);
}
