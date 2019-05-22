package com.kuhne_nagel.rate_fetcher.service;

import com.kuhne_nagel.rate_fetcher.client.CoinDeskRestClient;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyModel;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyResponse;
import com.kuhne_nagel.rate_fetcher.model.RateHistoricalDataModel;
import com.kuhne_nagel.rate_fetcher.service.impl.RateServiceImpl;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class RateServiceImplTest {

    @InjectMocks
    private RateServiceImpl rateService;

    @Mock
    private CoinDeskRestClient coinDeskRestClient;

    @Mock
    private RateModelMapper rateModelMapper;


    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void testGetRateByCurrency() {
        //Test data region
        final Currency currency = Currency.getInstance("EUR");
        final RateByCurrencyResponse rateByCurrencyResponse = new RateByCurrencyResponse();
        final RateByCurrencyModel expected = new RateByCurrencyModel(currency.getCurrencyCode(), 5.55555);
        //Mock region
        when(coinDeskRestClient.getRateByCurrency(eq(currency))).thenReturn(rateByCurrencyResponse);
        when(rateModelMapper.mapRateByCurrencyResponse(eq(currency), eq(rateByCurrencyResponse))).thenReturn(expected);
        final RateByCurrencyModel actual = rateService.getRateByCurrency(currency);
        //Verify region
        assertEquals(expected, actual);
        verify(coinDeskRestClient).getRateByCurrency(currency);
        verify(rateModelMapper).mapRateByCurrencyResponse(currency,rateByCurrencyResponse);
        verifyNoMoreInteractions(coinDeskRestClient,rateModelMapper);
    }


    @Test
    public void testGetRateHistoricalDateByCurrency() {
        //Test data region
        final Currency currency = Currency.getInstance("EUR");
        final LocalDate startDate  = LocalDate.now().minusMonths(1);
        final LocalDate endDate = LocalDate.now();
        final RateByCurrencyResponse rateByCurrencyResponse = new RateByCurrencyResponse();
        final RateHistoricalDataModel expected = new RateHistoricalDataModel(4.4444, 5.55555);
        //Mock region
        when(coinDeskRestClient.getRateByCurrencyHistoryData(eq(currency),eq(startDate),eq(endDate))).thenReturn(rateByCurrencyResponse);
        when(rateModelMapper.mapRateHistoricalDataResponse( eq(rateByCurrencyResponse))).thenReturn(expected);
        final RateHistoricalDataModel actual = rateService.getRateHistoricalDataByCurrency(currency,startDate,endDate);
        //Verify region
        assertEquals(expected, actual);
        verify(coinDeskRestClient).getRateByCurrencyHistoryData(currency,startDate,endDate);
        verify(rateModelMapper).mapRateHistoricalDataResponse(rateByCurrencyResponse);
        verifyNoMoreInteractions(coinDeskRestClient,rateModelMapper);
    }

}
