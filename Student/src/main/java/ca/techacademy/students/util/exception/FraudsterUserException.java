package ca.techacademy.students.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.UNAUTHORIZED ,reason = "Fraudster User")
public class FraudsterUserException extends RuntimeException {
    public FraudsterUserException(Object details) {
        log.debug("Fraudster request. Details = "+details);
    }
}
