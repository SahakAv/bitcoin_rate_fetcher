package com.kuhne_nagel.rate_fetcher.client;

import com.kuhne_nagel.rate_fetcher.exception.CoinDeskCommunicationException;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyResponse;
import com.kuhne_nagel.rate_fetcher.util.ApplicationPropertiesUtil;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.*;

public class CoinDeskRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoinDeskRestClient.class);

    private final Client client;

    private Integer connectionTimeout;

    private Integer readTimeout;

    private String coinDeskBaseUrl;

    private String currentPriceUrl;

    private String historicalUrl;


    public CoinDeskRestClient() {
        initProperties();
        client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT, connectionTimeout);
        client.property(ClientProperties.READ_TIMEOUT, readTimeout);
    }


    public RateByCurrencyResponse getRateByCurrency(final Currency currency) {
        LOGGER.debug("Getting current rate for a currency = {} ", currency.getCurrencyCode());
        final WebTarget webTarget = client.target(coinDeskBaseUrl);
        RateByCurrencyResponse response = null;
        try {
            response = webTarget
                    .path(String.format(currentPriceUrl, currency.getCurrencyCode())).request(MediaType.APPLICATION_JSON).get(RateByCurrencyResponse.class);

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching current rate for currency = {}", currency.getCurrencyCode());
            throw new CoinDeskCommunicationException(String.format("Error occurred while fetching current rate for currency %s", currency.getCurrencyCode()), e);
        }
        return response;
    }

    public RateByCurrencyResponse getRateByCurrencyHistoryData(Currency currency, LocalDate startDate, LocalDate endDate) {
        LOGGER.debug("Getting historical  rate date for a currency = {} with starDate ={} and endDate = {}", currency.getCurrencyCode(), startDate, endDate);

        final WebTarget webTarget = client.target(coinDeskBaseUrl + historicalUrl)
                .queryParam("currency", currency.getCurrencyCode())
                .queryParam("start", startDate)
                .queryParam("end", endDate);
        RateByCurrencyResponse rateByCurrencyResponse = null;
        try {
            rateByCurrencyResponse = webTarget.request(MediaType.APPLICATION_JSON).get(RateByCurrencyResponse.class);
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching historical rate data for currency = {}", currency.getCurrencyCode());
            throw new CoinDeskCommunicationException(String.format("Error occurred while fetching historical rate data for currency %s", currency.getCurrencyCode()), e);
        }
        return rateByCurrencyResponse;
    }


    private void initProperties() {
        connectionTimeout = Integer.valueOf(ApplicationPropertiesUtil.getProperty("client.connection.timeout"));
        readTimeout = Integer.valueOf(ApplicationPropertiesUtil.getProperty("client.read.timeout"));
        coinDeskBaseUrl = ApplicationPropertiesUtil.getProperty("coin.desk.base.url");
        currentPriceUrl = ApplicationPropertiesUtil.getProperty("coin.desk.currentPrice.url");
        historicalUrl= ApplicationPropertiesUtil.getProperty("coin.desk.historicalData.url");
    }

}
