package com.veem.client;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import com.veem.client.responses.AttachmentResponse;

/**
 * API endpoints.
 */
interface AttachmentApi
{
    String BASE = "/veem/v1.1/attachments";

    @Multipart
    @POST(BASE)
    Call<AttachmentResponse> upload(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file,
            @Header("Authorization") String authorization);

    @Streaming
    @GET(BASE)
    Call<ResponseBody> download(
            @Query("name") String fileName,
            @Query("referenceId") String referenceId,
            @Header("Authorization") String authorization);
}
