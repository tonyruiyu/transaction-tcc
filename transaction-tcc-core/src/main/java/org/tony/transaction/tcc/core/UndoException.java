package org.tony.transaction.tcc.core;

public class UndoException extends RuntimeException {
    private static final long serialVersionUID = -4768615839587297264L;

    public UndoException() {
        super();
    }

    public UndoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UndoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UndoException(String message) {
        super(message);
    }

    public UndoException(Throwable cause) {
        super(cause);
    }
}
