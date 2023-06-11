package io.wisoft.avocadobackendhexagonal.global.exception.duplicate;

import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateEmailException extends RuntimeException {

    private final ErrorCode errorCode;

    public DuplicateEmailException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
