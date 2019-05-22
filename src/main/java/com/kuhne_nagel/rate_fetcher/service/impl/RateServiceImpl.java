package com.kuhne_nagel.rate_fetcher.service.impl;

import com.kuhne_nagel.rate_fetcher.client.CoinDeskRestClient;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyModel;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyResponse;
import com.kuhne_nagel.rate_fetcher.model.RateHistoricalDataModel;
import com.kuhne_nagel.rate_fetcher.service.RateModelMapper;
import com.kuhne_nagel.rate_fetcher.service.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Currency;

public class RateServiceImpl implements RateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateService.class);
    private CoinDeskRestClient coinDeskRestClient;
    private RateModelMapper mapper;

    public RateServiceImpl() {
        this.coinDeskRestClient = new CoinDeskRestClient();
        this.mapper = new RateModelMapper();
    }

    @Override
    public RateByCurrencyModel getRateByCurrency(Currency currency) {
        final RateByCurrencyResponse rateByCurrencyResponse = coinDeskRestClient.getRateByCurrency(currency);
        final RateByCurrencyModel rateByCurrencyModel = mapper.mapRateByCurrencyResponse(currency, rateByCurrencyResponse);
        LOGGER.debug("Got rate = {} for currency = {}", rateByCurrencyModel, currency.getCurrencyCode());
        return rateByCurrencyModel;
    }

    @Override
    public RateHistoricalDataModel getRateHistoricalDataByCurrency(final Currency currency, final LocalDate startDate, final LocalDate endDate) {
        final RateByCurrencyResponse rateByCurrencyHistoryData = coinDeskRestClient.getRateByCurrencyHistoryData(currency, startDate, endDate);
        final RateHistoricalDataModel rateHistoricalDataModel = mapper.mapRateHistoricalDataResponse(rateByCurrencyHistoryData);
        LOGGER.debug("Got rate history data = {} for currency = {}", rateHistoricalDataModel, currency.getCurrencyCode());
        return rateHistoricalDataModel;
    }

}
