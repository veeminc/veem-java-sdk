package com.veem.test

import com.veem.client.VeemClient
import com.veem.client.VeemContext
import com.veem.model.Account
import com.veem.model.AccountListParameters
import com.veem.model.Page
import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import static com.veem.client.VeemContext.Environment.SANDBOX

class CustomerClient extends Specification
{
    @Shared config = new JsonSlurper().parse(getClass().getResource( "testConfig.json" ))
    @Shared VeemContext veemContext = new VeemContext.VeemContextBuilder()
            .environment(SANDBOX)
            .build()
    @Shared VeemClient veemClient = veemContext.getClient(config.token)

    def "+ List Accounts"()
    {
        given:
        AccountListParameters parameters = AccountListParameters.builder().email("test@test.com").build();

        when:
        Page<Account> accounts = veemClient.customers().list(parameters)

        then:
        noExceptionThrown()
    }

    def cleanup()
    {
        sleep(1000)
    }
}
