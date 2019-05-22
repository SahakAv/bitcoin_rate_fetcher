package com.kuhne_nagel.rate_fetcher.util;

import com.kuhne_nagel.rate_fetcher.exception.InvalidCurrencyCodeException;

import java.util.Currency;

public class CurrencyUtil {


    /**
     * @param currency - currency code
     * @return java.util.CurrencyCode
     *@throws InvalidCurrencyCodeException if the currency is invalid
     */
    public  Currency getCurrencyByCode(final String currency) {
        try {
            return Currency.getInstance(currency);
        } catch (IllegalArgumentException e) {
            throw new InvalidCurrencyCodeException(currency + " is invalid");
        }
    }
}
