package ca.techacademy.students.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR ,reason = "User internal server")
public class UserInternalServerException extends RuntimeException{
    public UserInternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInternalServerException() {
    }

    public UserInternalServerException(String message) {
        super(message);
    }

    public UserInternalServerException(Throwable cause) {
        super(cause);
    }

    public UserInternalServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
