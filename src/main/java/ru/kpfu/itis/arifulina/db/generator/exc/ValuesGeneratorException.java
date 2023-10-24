package ru.kpfu.itis.arifulina.db.generator.exc;

public class ValuesGeneratorException extends RuntimeException {
    public ValuesGeneratorException() {
    }

    public ValuesGeneratorException(String message) {
        super(message);
    }

    public ValuesGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValuesGeneratorException(Throwable cause) {
        super(cause);
    }

    public ValuesGeneratorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
