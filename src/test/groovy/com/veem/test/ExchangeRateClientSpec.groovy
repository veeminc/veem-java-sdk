package com.veem.test

import static com.neovisionaries.i18n.CountryCode.*
import static com.neovisionaries.i18n.CurrencyCode.*
import static com.veem.client.VeemContext.Environment.SANDBOX

import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant

import com.veem.client.VeemClient
import com.veem.client.VeemContext
import com.veem.model.ExchangeRate

class ExchangeRateClientSpec extends Specification
{
    @Shared config = new JsonSlurper().parse(getClass().getResource( "testConfig.json" ))
    @Shared VeemContext veemContext = new VeemContext.VeemContextBuilder()
            .environment(SANDBOX)
            .build()
    @Shared VeemClient veemClient = veemContext.getClient(config.token)

    def "+ Generate quote - CAD->USD - Sender amount"()
    {
        when:
        ExchangeRate exchangeRate = veemClient.exchangeRates().generate(ExchangeRate.builder()
                .fromAmount(10000)
                .fromCurrency(USD)
                .toCurrency(CAD)
                .toCountry(CA)
                .build())

        then:
        noExceptionThrown()
        exchangeRate.getFromAmount() == 10000
        exchangeRate.getFromCurrency() == USD
        exchangeRate.getToAmount() != null
        exchangeRate.getToCurrency() == CAD
        exchangeRate.getRate().round(6) == (exchangeRate.getToAmount() / exchangeRate.getFromAmount()).round(6)
        exchangeRate.getExpiry().after(Date.from(Instant.now()))
    }

    def "+ Generate quote - RUB->EUR - Receiver amount"()
    {
        when:
        ExchangeRate exchangeRate = veemClient.exchangeRates().generate(ExchangeRate.builder()
                .fromCurrency(RUB)
                .fromCountry(RU)
                .toAmount(10000)
                .toCurrency(EUR)
                .toCountry(DE)
                .build())

        then:
        noExceptionThrown()
        exchangeRate.getFromAmount() != null
        exchangeRate.getFromCurrency() == RUB
        exchangeRate.getToAmount() == 10000
        exchangeRate.getToCurrency() == EUR
        exchangeRate.getRate().round(6) == (exchangeRate.getToAmount() / exchangeRate.getFromAmount()).round(6)
        exchangeRate.getExpiry().after(Date.from(Instant.now()))
    }

    def "+ Generate quote - INR->CNY - Sender amount"()
    {
        when:
        ExchangeRate exchangeRate = veemClient.exchangeRates().generate(ExchangeRate.builder()
                .fromAmount(10000)
                .fromCurrency(INR)
                .toCurrency(CNY)
                .toCountry(CN)
                .build())

        then:
        noExceptionThrown()
        exchangeRate.getFromAmount() == 10000
        exchangeRate.getFromCurrency() == INR
        exchangeRate.getToAmount() != null
        exchangeRate.getToCurrency() == CNY
        exchangeRate.getRate().round(6) == (exchangeRate.getToAmount() / exchangeRate.getFromAmount()).round(6)
        exchangeRate.getExpiry().after(Date.from(Instant.now()))
    }

    def cleanup()
    {
        sleep(2000)
    }
}
