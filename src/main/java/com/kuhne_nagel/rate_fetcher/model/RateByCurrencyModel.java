package com.kuhne_nagel.rate_fetcher.model;

import java.util.Objects;

public class RateByCurrencyModel {

    private String currency;

    private Double rate;

    public RateByCurrencyModel(String currency, Double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateByCurrencyModel that = (RateByCurrencyModel) o;
        return Objects.equals(currency, that.currency) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, rate);
    }

    @Override
    public String toString() {
        return "RateByCurrencyModel{" +
                "currency=" + currency +
                ", rate=" + rate +
                '}';
    }
}
