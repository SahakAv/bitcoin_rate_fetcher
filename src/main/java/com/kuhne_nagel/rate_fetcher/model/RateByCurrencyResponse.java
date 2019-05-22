package com.kuhne_nagel.rate_fetcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateByCurrencyResponse {

    private JsonNode bpi;


    public RateByCurrencyResponse() {
    }

    public JsonNode getBpi() {
        return bpi;
    }

    public void setBpi(JsonNode bpi) {
        this.bpi = bpi;
    }


    @Override
    public String toString() {
        return "RateByCurrencyResponse{" +
                "bpi=" + bpi +
                '}';
    }
}
