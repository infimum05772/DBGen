package ru.kpfu.itis.arifulina.db.generator.exc;

public class StringsGeneratorException extends Exception {
    public StringsGeneratorException() {
    }

    public StringsGeneratorException(String message) {
        super(message);
    }

    public StringsGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public StringsGeneratorException(Throwable cause) {
        super(cause);
    }

    public StringsGeneratorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
