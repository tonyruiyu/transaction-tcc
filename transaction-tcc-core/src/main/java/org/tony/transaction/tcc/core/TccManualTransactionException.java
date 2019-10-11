package org.tony.transaction.tcc.core;

public class TccManualTransactionException extends RuntimeException {

    private static final long serialVersionUID = -5936194800650864942L;

    public TccManualTransactionException() {
        super();
    }

    public TccManualTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TccManualTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TccManualTransactionException(String message) {
        super(message);
    }

    public TccManualTransactionException(Throwable cause) {
        super(cause);
    }

}
