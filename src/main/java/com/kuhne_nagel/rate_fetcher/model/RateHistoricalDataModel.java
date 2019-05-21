package com.kuhne_nagel.rate_fetcher.model;

import java.util.Objects;

public class RateHistoricalDataModel {

    private Double lowestRate;

    private Double highestRate;

    public RateHistoricalDataModel(Double lowestRate, Double highestRate) {
        this.lowestRate = lowestRate;
        this.highestRate = highestRate;
    }

    public Double getLowestRate() {
        return lowestRate;
    }

    public void setLowestRate(Double lowestRate) {
        this.lowestRate = lowestRate;
    }

    public Double getHighestRate() {
        return highestRate;
    }

    public void setHighestRate(Double highestRate) {
        this.highestRate = highestRate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RateHistoricalDataModel that = (RateHistoricalDataModel) o;
        return Objects.equals(lowestRate, that.lowestRate) &&
                Objects.equals(highestRate, that.highestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowestRate, highestRate);
    }

    @Override
    public String toString() {
        return "RateHistoricalDataModel{" +
                "lowestRate=" + lowestRate +
                ", highestRate=" + highestRate +
                '}';
    }
}

