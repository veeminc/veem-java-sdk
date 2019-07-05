package com.veem.test

import static com.veem.client.VeemContext.Environment.SANDBOX

import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import com.veem.client.VeemClient
import com.veem.client.VeemContext
import com.veem.client.responses.CountryInfoResponse
import com.veem.model.CountryInfo

class MetadataClientSpec extends Specification
{
    @Shared config = new JsonSlurper().parse(getClass().getResource( "testConfig.json" ))
    @Shared VeemContext veemContext = new VeemContext.VeemContextBuilder()
            .environment(SANDBOX)
            .build()
    @Shared VeemClient veemClient = veemContext.getClient(config.token)

    def "+ Get country currency map"()
    {
        when:
        List<CountryInfo> countryInfo = veemClient.metadata().getCountryCurrencyMap()

        then:
        noExceptionThrown()
        !countryInfo.isEmpty()
    }

    def cleanup()
    {
        sleep(2000)
    }
}
