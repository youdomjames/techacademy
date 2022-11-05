package ca.techacademy.students.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Field not supported")
public class FieldNotSupportedException extends RuntimeException{
    public FieldNotSupportedException() {
    }

    public FieldNotSupportedException(String message) {
        super(message);
    }

    public FieldNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldNotSupportedException(Throwable cause) {
        super(cause);
    }

    public FieldNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
