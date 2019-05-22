package com.kuhne_nagel.rate_fetcher.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyModel;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyResponse;
import com.kuhne_nagel.rate_fetcher.model.RateHistoricalDataModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Currency;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class RateModelMapperTest {

    @InjectMocks
    private RateModelMapper rateModelMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapRateByCurrencyResponse() throws IOException {
        final Currency currency = Currency.getInstance("AMD");
        final Double rate = 3795509.7326;
        final RateByCurrencyResponse rateByCurrencyResponse = new RateByCurrencyResponse();
        rateByCurrencyResponse.setBpi(new ObjectMapper().readTree(getTestJsonForCurrentRate(rate)));
        RateByCurrencyModel rateByCurrencyModel = rateModelMapper.mapRateByCurrencyResponse(currency, rateByCurrencyResponse);
        assertEquals(rateByCurrencyModel.getCurrency(), currency.getCurrencyCode());
        assertEquals(rateByCurrencyModel.getRate(), rateByCurrencyModel.getRate());
    }

    @Test
    public void testMapRateHistoricalDataResponse() throws IOException {
        final Double lowestRate = 11111.11111;
        final Double highestRate = 999999999.99999;
        final RateByCurrencyResponse rateByCurrencyResponse = new RateByCurrencyResponse();
        rateByCurrencyResponse.setBpi(new ObjectMapper().readTree(getTestJsonFurHistoricalDate()));
        RateHistoricalDataModel rateHistoricalDataModel = rateModelMapper.mapRateHistoricalDataResponse(rateByCurrencyResponse);
        assertEquals(rateHistoricalDataModel.getLowestRate(), lowestRate);
        assertEquals(rateHistoricalDataModel.getHighestRate(), highestRate);

    }



    private String getTestJsonForCurrentRate(final Double rate) {
        return "{\"USD\":{\"code\":\"USD\",\"rate\":\"7,919.4767\"," +
                "\"description\":\"United States Dollar\",\"rate_float\":7919.4767},\"AMD\":{\"code\":\"AMD\",\"rate\":\"3,795,509.7326\"," +
                "\"description\":\"Armenian Dram\",\"rate_float\":" + rate + "}}";
    }

    private String getTestJsonFurHistoricalDate(){
       return  "{\"2019-04-21\":11111.11111,\"2019-04-22\":2605842.6083,\"2019-04-23\":2669495.2482," +
               "\"2019-04-24\":999999999.99999,\"2019-04-25\":2486955.0646,\"2019-04-26\":2525427.1143," +
               "\"2019-04-27\":2528421.8586,\"2019-04-28\":2531027.1493,\"2019-04-29\":2532210.7424," +
               "\"2019-04-30\":2589963.2731,\"2019-05-01\":2610577.312,\"2019-05-02\":2640577.4674}";
    }

}
