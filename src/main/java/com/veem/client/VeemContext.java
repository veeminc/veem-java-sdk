package com.veem.client;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import com.veem.constants.Scope;
import com.veem.exceptions.VeemSdkException;
import com.veem.model.TokenResponse;

/**
 *
 */
@Builder
@Slf4j
public final class VeemContext
{
    /**
     *
     */
    public enum Environment
    {
        PRODUCTION("https://api.veem.com"),
        SANDBOX("https://sandbox-api.veem.com");

        String baseUrl;

        Environment(String baseUrl)
        {
            this.baseUrl = baseUrl;
        }
    }

    public static class VeemContextBuilder
    {
        // Exclude fields from builder by declaring private builder methods
        @SuppressWarnings("unused") private VeemContextBuilder attachmentApi(AttachmentApi a) {return this; }
        @SuppressWarnings("unused") private VeemContextBuilder authenticationApi(AuthenticationApi a) { return this; }
        @SuppressWarnings("unused") private VeemContextBuilder contactApi(ContactApi a) {return this; }
        @SuppressWarnings("unused") private VeemContextBuilder exchangeRateApi(ExchangeRateApi a) {return this; }
        @SuppressWarnings("unused") private VeemContextBuilder invoiceApi(InvoiceApi a) { return this; }
        @SuppressWarnings("unused") private VeemContextBuilder metadataApi(MetadataApi a) {return this; }
        @SuppressWarnings("unused") private VeemContextBuilder paymentApi(PaymentApi a) { return this; }
        @SuppressWarnings("unused") private VeemContextBuilder customerApi(String a) {return this; }
        @SuppressWarnings("unused") private VeemContextBuilder encodedClientCredentials(String a) {return this; }

    }

    private static final String SANDBOX_FINGER_PRINT  = "QkE6NjA6RTM6OTc6Qjc6ODg6MEE6OTM6QkE6NDQ6NDc6NTU6MTY6NzM6MkY6MTc6ODg6RjE6QkI6MzI=";
    private static final String PRODUCTION_FINGER_PRINT = "Qzc6MjE6Q0E6QjI6OUQ6ODU6NzM6OUU6M0E6MDA6Q0Y6ODY6RUM6MzY6RDA6NUM6NEY6MTY6MTg6OUM=";
    private static final String FINGER_PRINT_HASHING = "sha1/";


    
    @Getter @Default private final Environment environment = Environment.SANDBOX;
    @Getter @Default private final String clientId = null;
    @Getter @Default private final String clientSecret = null;
    @Getter @Default private final int connectionTimeout = 10000;
    @Getter @Default private final int readTimeout = 10000;
    @Getter @Default private final int retryAttempts = 0;

    AttachmentApi attachmentApi;
    AuthenticationApi authenticationApi;
    ContactApi contactApi;
    ExchangeRateApi exchangeRateApi;
    InvoiceApi invoiceApi;
    MetadataApi metadataApi;
    PaymentApi paymentApi;
    CustomerApi customerApi;

    private String encodedClientCredentials;

    /**
     *
     * @param accessToken An OAuth 2.0 access token
     * @return
     */
    public VeemClient getClient(final String accessToken)
    {
        if (this.authenticationApi == null)
        {
            initializeApi();
        }
        return new VeemClient(this, accessToken);
    }

    public AuthenticationClient getAuthenticationClient()
    {
        if (this.authenticationApi == null)
        {
            initializeApi();
        }

        return new AuthenticationClient(this);
    }


    private void initializeApi()
    {

        val certificatePinner = new CertificatePinner.Builder()
                .add(Environment.SANDBOX.name(),FINGER_PRINT_HASHING + SANDBOX_FINGER_PRINT)
                .add(Environment.PRODUCTION.name(), FINGER_PRINT_HASHING + PRODUCTION_FINGER_PRINT)
                .build();

        val httpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(log::info).setLevel(BASIC))
                .addInterceptor(new HttpLoggingInterceptor(log::debug).setLevel(BODY))
                .connectTimeout(this.connectionTimeout, MILLISECONDS)
                .readTimeout(this.readTimeout, MILLISECONDS)
                .certificatePinner(certificatePinner)
                .build();

        val retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                .client(httpClient)
                .baseUrl(this.environment.baseUrl)
                .build();

        this.attachmentApi = retrofit.create(AttachmentApi.class);
        this.authenticationApi = retrofit.create(AuthenticationApi.class);
        this.contactApi = retrofit.create(ContactApi.class);
        this.exchangeRateApi = retrofit.create(ExchangeRateApi.class);
        this.invoiceApi = retrofit.create(InvoiceApi.class);
        this.metadataApi = retrofit.create(MetadataApi.class);
        this.paymentApi = retrofit.create(PaymentApi.class);
        this.customerApi = retrofit.create(CustomerApi.class);
    }
}
