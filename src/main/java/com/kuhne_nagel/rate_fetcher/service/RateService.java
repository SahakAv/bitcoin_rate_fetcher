package com.kuhne_nagel.rate_fetcher.service;

import com.kuhne_nagel.rate_fetcher.exception.PropertyNotFoundException;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyModel;
import com.kuhne_nagel.rate_fetcher.model.RateHistoricalDataModel;

import java.time.LocalDate;
import java.util.Currency;

public interface RateService {

    /**
     * Getting current rate for provided currency
     * @param currency - Currency for which will be fetched
     *@return  RateByCurrencyModel
     */
    RateByCurrencyModel getRateByCurrency(final Currency currency);


    /**
     * Getting current rate for provided currency
     * @param currency - Currency for which will be fetched
     * @param startDate - Started from which date historical date will be fetched
     * @param endDate - Started until which date historical date will be fetched
     *@return  RateHistoricalDataModel
     */
    RateHistoricalDataModel getRateHistoricalDataByCurrency(final Currency currency, final LocalDate startDate, final LocalDate endDate);
}
