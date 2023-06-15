package io.wisoft.avocadobackendhexagonal.global.exception.token;

import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private ErrorCode errorCode;

    public InvalidTokenException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
