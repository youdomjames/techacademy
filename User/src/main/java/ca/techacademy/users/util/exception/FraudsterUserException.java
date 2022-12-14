package ca.techacademy.users.util.exception;

import ca.techacademy.users.model.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@Slf4j
@ResponseStatus(value = HttpStatus.UNAUTHORIZED ,reason = "Fraudster User")
public class FraudsterUserException extends RuntimeException {
    public FraudsterUserException(Object details) {
        log.debug("Fraudster request. Details = "+details);
    }
}
