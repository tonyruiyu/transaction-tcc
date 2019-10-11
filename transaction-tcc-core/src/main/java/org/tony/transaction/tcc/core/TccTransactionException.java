package org.tony.transaction.tcc.core;

public class TccTransactionException extends RuntimeException {

    private static final long serialVersionUID = -5936194800650864942L;

    public TccTransactionException() {
        super();
    }

    public TccTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TccTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TccTransactionException(String message) {
        super(message);
    }

    public TccTransactionException(Throwable cause) {
        super(cause);
    }

}
