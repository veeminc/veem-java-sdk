package com.veem.client;

import com.veem.client.responses.AccountResponse;
import com.veem.client.responses.PageResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CustomerApi
{
    @GET("/veem/v1.1/customers")
    Call<PageResponse<AccountResponse>> list(
            @Query("email") String email,
            @Header("Authorization") String authorization);
}
