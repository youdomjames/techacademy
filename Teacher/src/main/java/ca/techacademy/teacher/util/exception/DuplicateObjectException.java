package ca.techacademy.students.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT ,reason = "Duplicate object")
public class DuplicateObjectException extends RuntimeException{
    public DuplicateObjectException(String message) {
        super(message);
    }

    public DuplicateObjectException() {
    }
}
