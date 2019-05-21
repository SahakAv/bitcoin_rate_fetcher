package com.kuhne_nagel.rate_fetcher.exception;

public class CoinDeskCommunicationException extends RuntimeException {


    public CoinDeskCommunicationException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
