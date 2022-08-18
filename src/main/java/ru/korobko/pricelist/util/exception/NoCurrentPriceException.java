package ru.korobko.pricelist.util.exception;

public class NoCurrentPriceException extends Exception{
    public NoCurrentPriceException() {
        super();
    }

    public NoCurrentPriceException(String message) {
        super(message);
    }
}
