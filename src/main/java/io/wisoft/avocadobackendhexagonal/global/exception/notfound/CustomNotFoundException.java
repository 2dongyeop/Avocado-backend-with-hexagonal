package io.wisoft.avocadobackendhexagonal.global.exception.notfound;

import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomNotFoundException() {
        this.errorCode = ErrorCode.NOT_FOUND;
    }

    public CustomNotFoundException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
