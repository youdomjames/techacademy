package ca.techacademy.users.util.exception;

import ca.techacademy.users.model.Profile;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class FraudsterUserException extends RuntimeException {
    public FraudsterUserException(Object details) {
        log.debug("Fraudster request. Details = "+details);
    }
}
