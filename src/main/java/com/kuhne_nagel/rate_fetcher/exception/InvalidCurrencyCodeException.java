package com.kuhne_nagel.rate_fetcher.exception;

public class InvalidCurrencyCodeException extends RuntimeException {


    public InvalidCurrencyCodeException(final String message) {
        super(message);
    }
}
