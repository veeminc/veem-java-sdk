package com.veem.test

import com.veem.client.AuthenticationClient
import com.veem.client.VeemContext
import com.veem.constants.Scope
import com.veem.model.TokenResponse
import groovy.json.JsonSlurper
import spock.lang.Specification

import static com.veem.client.VeemContext.Environment.SANDBOX

class AuthenticationClientSpec extends Specification {

    // Write the authorization code, it will be invalidated once used.
    static final AUTHORIZATION_CODE = "authorization_code"
    static final REDIRECT_URL = "http://localhost.com"

    def "Get access token from client credentials flow"() {
        setup: "Setup the authentication context for client credential flow"
        def config = new JsonSlurper().parse(getClass().getResource("testConfigClientCreds.json"))
        VeemContext veemContext = new VeemContext.VeemContextBuilder()
                .environment(SANDBOX)
                .clientId(config.clientId)
                .clientSecret(config.clientSecret)
                .build()

        when:
        AuthenticationClient authenticationClient = veemContext.getAuthenticationClient()
        TokenResponse tokenResponse = authenticationClient.getTokenFromClientCredentials(
                Arrays.asList(Scope.ALL)
        )

        then:
        noExceptionThrown()
        tokenResponse.accessToken != null
        tokenResponse.expiresIn != null
    }


    def "Get access token from authorization code flow"()
    {
        setup: "Setup authentication context for authorization flow"
        def config = new JsonSlurper().parse(getClass().getResource("testConfigAuth.json"))
        VeemContext veemContext = new VeemContext.VeemContextBuilder()
                .environment(SANDBOX)
                .clientId(config.clientId)
                .clientSecret(config.clientSecret)
                .build()

        when:
        AuthenticationClient authenticationClient = veemContext.getAuthenticationClient()
        TokenResponse tokenResponse = authenticationClient.getTokenFromAuthorizationCode(
                AUTHORIZATION_CODE, REDIRECT_URL,Arrays.asList(Scope.ALL))

        then:
        noExceptionThrown()
    }


    def "Refresh token for authorization code flow"()
    {
        setup: "Setup authentication context for authorization flow"
        def config = new JsonSlurper().parse(getClass().getResource("testConfigAuth.json"))
        VeemContext veemContext = new VeemContext.VeemContextBuilder()
                .environment(SANDBOX)
                .clientId(config.clientId)
                .clientSecret(config.clientSecret)
                .build()

        when:
        AuthenticationClient authenticationClient = veemContext.getAuthenticationClient()
        authenticationClient.refreshToken(config.refreshToken)

        then:
        noExceptionThrown()
    }

    def "Get authorization code url"() {
        setup: "Setup authentication context for authorization flow"
        def config = new JsonSlurper().parse(getClass().getResource("testConfigAuth.json"))
        VeemContext veemContext = new VeemContext.VeemContextBuilder()
                .environment(SANDBOX)
                .clientId(config.clientId)
                .clientSecret(config.clientSecret)
                .build()

        when:
        AuthenticationClient authenticationClient = veemContext.getAuthenticationClient()
        def authUrl = authenticationClient.getAuthorizationUrl("http://localhost.com")

        then:
        noExceptionThrown()
        authUrl != null
    }

    def cleanup()
    {
        sleep(2000)
    }
}