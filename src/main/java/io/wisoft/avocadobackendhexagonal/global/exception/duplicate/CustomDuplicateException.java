package io.wisoft.avocadobackendhexagonal.global.exception.duplicate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "duplicate entity")
public class CustomDuplicateException extends RuntimeException {
    public CustomDuplicateException(final String message) {
        super(message);
    }
}
