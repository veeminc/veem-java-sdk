package com.veem.client;

import com.veem.constants.Scope;
import com.veem.exceptions.VeemException;
import com.veem.exceptions.VeemSdkException;
import com.veem.model.Token;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static com.veem.client.ResponseHandler.handleResponse;

public class AuthenticationClient
{
    private static final String AUTHORIZATION_CODE_GRANT_TYPE = "authorization_code";
    private static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
    private static final String CLIENT_CREDENTIALS_GRANT_TYPE = "client_credentials";

    private final VeemContext veemContext;
    private final AuthenticationApi authenticationApi;

    public AuthenticationClient(final VeemContext veemContext)
    {
        this.veemContext = veemContext;
        this.authenticationApi = veemContext.authenticationApi;
    }

    /**
     * Builds the Authorization flow url.
     * @param redirectUrl - Optional field
     *
     * @return - Returns the authorization code url for the three legged flow.
     * @throws VeemSdkException
     */
    public String getAuthorizationUrl(final String redirectUrl) throws VeemSdkException {
        try
        {
            return String.format("%s/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s",
                    veemContext.getEnvironment().baseUrl,
                    veemContext.getClientId(),
                    URLEncoder.encode(redirectUrl, "UTF-8"));
        }
        catch (UnsupportedEncodingException ex)
        {
            throw new VeemSdkException("Unsupported encoding exception");
        }
    }

    /**
     *
     * @param authorizationCode
     * @param scopes
     * @return
     * @throws IOException
     */
    public Token getTokenFromAuthorizationCode(
            final String authorizationCode,
            final String redirectUrl,
            final List<Scope> scopes) throws IOException, VeemSdkException
    {
        if (authorizationCode == null || authorizationCode.trim().isEmpty())
        {
            throw new VeemSdkException("Authorization code cannot be empty or null");
        }

        return authenticationApi
                .getToken(
                        getEncodedClientCredentials(),
                        AUTHORIZATION_CODE_GRANT_TYPE,
                        authorizationCode,
                        null,
                        URLEncoder.encode(redirectUrl, "UTF-8"),
                        scopes.stream().map(Scope::getStringValue).collect(Collectors.toList()))
                .execute()
                .body();
    }

    /**
     *
     * @param scopes
     * @return
     * @throws IOException
     */
    public Token getTokenFromClientCredentials(final List<Scope> scopes) throws VeemException
    {
        return handleResponse(authenticationApi
                .getToken(
                        getEncodedClientCredentials(),
                        CLIENT_CREDENTIALS_GRANT_TYPE,
                        null,
                        null,
                        null,
                        scopes.stream().map(Scope::getStringValue).collect(Collectors.toList()))::execute);
    }

    /**
     *
     * @param refreshToken
     * @return
     * @throws IOException
     */
    public Token refreshToken(final String refreshToken) throws VeemException
    {
        if (refreshToken == null || refreshToken.trim().isEmpty())
        {
            throw new VeemSdkException("Refresh token cannot be empty or null");
        }

        return handleResponse(authenticationApi
                .getToken(
                        getEncodedClientCredentials(),
                        REFRESH_TOKEN_GRANT_TYPE,
                        null,
                        refreshToken,
                        null,
                        null)::execute);
    }

    private String getEncodedClientCredentials() throws VeemSdkException
    {
        if (veemContext.getClientId() == null || veemContext.getClientSecret() == null)
        {
            throw new VeemSdkException("Client credentials not configured in VeemContext");
        }

        return  String.format("Basic %s", Base64.getEncoder()
                .encodeToString(String.format("%s:%s", veemContext.getClientId(), veemContext.getClientSecret()).getBytes()));
    }
}
