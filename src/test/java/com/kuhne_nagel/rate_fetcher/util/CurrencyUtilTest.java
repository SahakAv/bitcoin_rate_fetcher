package com.kuhne_nagel.rate_fetcher.util;

import com.kuhne_nagel.rate_fetcher.exception.InvalidCurrencyCodeException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import java.util.Currency;

import static org.junit.Assert.fail;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(DataProviderRunner.class)
public class CurrencyUtilTest {

    @InjectMocks
    private CurrencyUtil currencyUtil;

    @Before
    public void init(){
        initMocks(this);
    }

    @Test(expected = InvalidCurrencyCodeException.class)
    @UseDataProvider
    public void testGetCurrencyByCodeWithInvalidArguments(String currencyCode){
        currencyUtil.getCurrencyByCode(currencyCode);
        //Exception should be thrown
        fail();
    }

    @Test
    @UseDataProvider
    public void testGetCurrencyByCode(String currencyCode){
        final Currency currencyByCode = currencyUtil.getCurrencyByCode(currencyCode);
        Assert.assertNotNull(currencyByCode);
    }

    @DataProvider
    public static Object[][]testGetCurrencyByCodeWithInvalidArguments(){
        return new Object[][]{
                {"s"},
                {"EE"},
                {"DAA"}
        };
    }

    @DataProvider
    public static Object[][]testGetCurrencyByCode(){
        return new Object[][]{
                {"AMD"},
                {"USD"},
                {"EUR"}
        };
    }
}
