package io.wisoft.avocadobackendhexagonal.global.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {

    //COMMON
    NOT_FOUND(404, "Common-NotFound-404", "해당 엔티티를 찾을 수 없습니다."),

    //DUPLICATE,
    DUPLICATE_EMAIL(400, "Duplicate-Mail-400", "Email is duplicated"),
    DUPLICATE_HOSPITAL(400, "Duplicate-Hospital-400", "Hospital name is duplicated"),

    //SYSTEM
    INTERNAL_SERVER_ERROR(500, "System-500", "Internal server error"),
    HANDLE_ACCESS_DENIED(403, "System-403", "Handle access denied"),
    METHOD_NOT_ALLOWED(405, "System-405", "Method not allowed"),
    METHOD_ARGUMENT_TYPE_MISMATCH(400, "System-400", "Method argument type mismatch"),
    INVALID_INPUT_VALUE(400, "Sytem-400", "Invalid input value"),

    //Illegal
    INVALID_PASSWORD(400, "Illegal-400", "Invalid password1"),
    ILLEGAL_INPUT(400, "Illegal-400", "Invalid input"),
    INVALID_TOKEN(400, "Token-400", "Invalid token"),
    EXPIRED_TOKEN(400, "Token-400", "Expired token");

    private int httpStatusCode;
    private String errorCode;
    private String message;

    ErrorCode(final int httpStatusCode, final String errorCode, final String message) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
