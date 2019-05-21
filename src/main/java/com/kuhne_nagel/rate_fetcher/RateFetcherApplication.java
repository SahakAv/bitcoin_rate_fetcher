package com.kuhne_nagel.rate_fetcher;


import com.kuhne_nagel.rate_fetcher.exception.InvalidCurrencyCodeException;
import com.kuhne_nagel.rate_fetcher.model.RateHistoricalDataModel;
import com.kuhne_nagel.rate_fetcher.service.RateService;
import com.kuhne_nagel.rate_fetcher.service.impl.RateServiceImpl;
import com.kuhne_nagel.rate_fetcher.util.CurrencyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class RateFetcherApplication {

    private final RateService rateService;

    private final Logger LOGGER = LoggerFactory.getLogger(RateFetcherApplication.class);

    public RateFetcherApplication() {
        rateService = new RateServiceImpl();
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);
        LOGGER.info("Please input currency for which rate will be fetched");
        final String currency = scanner.next();
        try {
            Currency currencyByCode = CurrencyUtil.getCurrencyByCode(currency);
            fetchRateForCurrency(currencyByCode);
        } catch (InvalidCurrencyCodeException e) {
            LOGGER.error("Invalid currency provided please input correct one ");
        }
    }

    private void fetchRateForCurrency(final Currency currency) {
        CompletableFuture.supplyAsync(() -> rateService.getRateByCurrency(currency))
                .whenComplete((rate, throwable) -> LOGGER.info("Current rate for currency = '{}'is = '{}' ", rate.getCurrency(), rate.getRate()));
        RateHistoricalDataModel rateHistory = rateService.getRateHistoricalDateByCurrency(currency, LocalDate.now().minusMonths(1), LocalDate.now());
        LOGGER.info("Lowest rate for currency = '{}' is  ='{}' and highest rate is = '{}'", currency, rateHistory.getLowestRate(), rateHistory.getHighestRate());
    }


}