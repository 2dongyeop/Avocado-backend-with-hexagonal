package io.wisoft.avocadobackendhexagonal.global.exception.Illegal;

import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomIllegalException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomIllegalException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
