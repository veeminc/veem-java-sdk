package com.veem.client;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

import com.veem.model.TokenResponse;

/**
 * API endpoints.
 */
interface AuthenticationApi
{
    @POST("/oauth/token")
    Call<TokenResponse> getToken(
            @Header("Authorization") String encodedCredentials,
            @Query("grant_type") String grantType,
            @Query("code") String code,
            @Query("refresh_token") String refresh_token,
            @Query("redirect_uri") String redirectUrl,
            @Query("scope") List<String> scope);
}