package io.wisoft.avocadobackendhexagonal.global.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int httpStatusCode;
    private String errorCode;
    private String message;

    public ErrorResponse(final ErrorCode errorCode) {
        this.httpStatusCode = errorCode.getHttpStatusCode();
        this.errorCode = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }
}
