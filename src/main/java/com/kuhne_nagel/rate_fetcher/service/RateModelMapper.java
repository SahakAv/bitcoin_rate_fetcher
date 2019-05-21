package com.kuhne_nagel.rate_fetcher.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyModel;
import com.kuhne_nagel.rate_fetcher.model.RateByCurrencyResponse;
import com.kuhne_nagel.rate_fetcher.model.RateHistoricalDataModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RateModelMapper {

    public RateByCurrencyModel mapRateByCurrencyResponse(final Currency currency, final RateByCurrencyResponse rateByCurrencyResponse) {
        final String code = currency.getCurrencyCode();
        final Double rate = rateByCurrencyResponse.getBpi().get(code).get("rate_float").asDouble();
        return new RateByCurrencyModel(code, rate);
    }

    public RateHistoricalDataModel mapRateHistoricalDataResponse(final RateByCurrencyResponse rateByCurrencyResponse) {
        final Iterator<Map.Entry<String, JsonNode>> fields = rateByCurrencyResponse.getBpi().fields();
        final TreeSet<Double> rateHistory = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(fields, Spliterator.ORDERED),
                false).map(Map.Entry::getValue).map(JsonNode::asDouble).collect(Collectors.toCollection(TreeSet::new));
        return new RateHistoricalDataModel(rateHistory.first(),rateHistory.last());
    }
}
