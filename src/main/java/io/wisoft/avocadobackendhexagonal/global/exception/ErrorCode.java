package io.wisoft.avocadobackendhexagonal.global.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {

    //COMMON
    ASSERT_INVALID_INPUT(400, "Common-Builder-400", "Builder가 요구하는 필수 파라미터가 요구되지 않았습니다."),
    NOT_FOUND(404, "Common-NotFound-404", "해당 엔티티를 찾을 수 없습니다."),
    TIME_OUT(500, "Common-TimeOut-500", "Timeout 발생"),

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
    EXPIRED_TOKEN(400, "Token-400", "Expired token"),
    ALREADY_LOGOUT_TOKEN(403, "Token-403", "Already logout token"),
    JWT_EXCEPTION(400, "Token-400", "JWT is invalid"),
    NOT_EXIST_TOKEN(401, "Illegal-Not-Exist-Token-401", "Token is not exist");

    private int httpStatusCode;
    private String errorCode;
    private String message;

    ErrorCode(final int httpStatusCode, final String errorCode, final String message) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
