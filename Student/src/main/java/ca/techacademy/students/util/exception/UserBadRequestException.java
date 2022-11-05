package ca.techacademy.students.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST ,reason = "User bad request")
public class UserBadRequestException extends RuntimeException{
    public UserBadRequestException() {
    }

    public UserBadRequestException(String message) {
        super(message);
    }

    public UserBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserBadRequestException(Throwable cause) {
        super(cause);
    }

    public UserBadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
