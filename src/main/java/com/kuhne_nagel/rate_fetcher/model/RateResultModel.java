package com.kuhne_nagel.rate_fetcher.model;

import java.util.Currency;
import java.util.Objects;

public class RateResultModel {

    private Currency currency;

    private Double rate;


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
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
        RateResultModel that = (RateResultModel) o;
        return Objects.equals(currency, that.currency) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, rate);
    }

    @Override
    public String toString() {
        return "RateResultModel{" +
                "currency=" + currency +
                ", rate=" + rate +
                '}';
    }
}
