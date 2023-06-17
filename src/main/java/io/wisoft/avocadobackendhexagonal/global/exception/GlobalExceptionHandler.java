package io.wisoft.avocadobackendhexagonal.global.exception;

import io.jsonwebtoken.JwtException;
import io.wisoft.avocadobackendhexagonal.global.exception.duplicate.DuplicateEmailException;
import io.wisoft.avocadobackendhexagonal.global.exception.duplicate.DuplicateHospitalException;
import io.wisoft.avocadobackendhexagonal.global.exception.notfound.CustomNotFoundException;
import io.wisoft.avocadobackendhexagonal.global.exception.token.AlreadyLogoutException;
import io.wisoft.avocadobackendhexagonal.global.exception.token.ExpiredTokenException;
import io.wisoft.avocadobackendhexagonal.global.exception.token.InvalidTokenException;
import io.wisoft.avocadobackendhexagonal.global.exception.token.NotExistTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Builder 사용으로 인해 요구된 파라미터 유효성 검사시
     * Assert.nonNull, Assert.hasText 등을 통해 발생되는 예외
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException exception) {

        log.error("handleIllegalArgumentException", exception);
        final ErrorResponse response = new ErrorResponse(ErrorCode.ASSERT_INVALID_INPUT);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getHttpStatusCode()));
    }


    /**
     * 엔티티 조회 실패시
     */
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final CustomNotFoundException exception) {

        log.error("handlerNotFoundException", exception);
        return getResponse(exception.getErrorCode());
    }


    /**
     * java.util.concurrent.TimeoutException
     * 제한시간을 지나 타임아웃시 발생하는 예외
     */
    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponse> handleTimeoutException(final TimeoutException exception) {

        log.error("handleTimeoutException", exception);
        return getResponse(ErrorCode.TIME_OUT);
    }


    /**
     * 이메일 중복시
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(final DuplicateEmailException exception) {

        log.error("handleDuplicateEmailException", exception);
        return getResponse(exception.getErrorCode());
    }


    /**
     * 병원 중복시
     */
    @ExceptionHandler(DuplicateHospitalException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateHospitalException(final DuplicateHospitalException exception) {

        log.error("handleDuplicateHospitalException", exception);
        return getResponse(exception.getErrorCode());
    }

    private ResponseEntity<ErrorResponse> getResponse(final ErrorCode errorCode) {
        final ErrorResponse errorResponse = new ErrorResponse(errorCode);
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorCode.getHttpStatusCode()));
    }


    /**
     * 토큰이 유효하지 않은 경우
     */
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(final InvalidTokenException exception) {

        log.error("handleInvalidTokenException", exception);
        return getResponse(exception.getErrorCode());
    }


    /**
     * 토큰이 만료시간이 지난 경우
     */
    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> handleExpiredTokenException(final ExpiredTokenException exception) {

        log.error("handleExpiredTokenException", exception);
        return getResponse(exception.getErrorCode());
    }


    /**
     * 로그아웃된 토큰으로 요청할 경우
     */
    @ExceptionHandler(AlreadyLogoutException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyLogoutException(final AlreadyLogoutException exception) {

        log.error("handleAlreadyLogoutException", exception);
        return getResponse(exception.getErrorCode());
    }


    /**
     * JWT exception
     */
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(final JwtException exception) {

        log.error("handleJwtException", exception);
        return getResponse(ErrorCode.JWT_EXCEPTION);
    }


    /**
     * JWT 토큰이 적재되지 않았을 경우
     */
    @ExceptionHandler(NotExistTokenException.class)
    public ResponseEntity<ErrorResponse> handleNotExistTokenException(final NotExistTokenException exception) {

        log.error("handleNotExistTokenException", exception);
        return getResponse(exception.getErrorCode());
    }


    /**
     *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final ErrorResponse response = new ErrorResponse(ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        final ErrorResponse response = new ErrorResponse(ErrorCode.HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.getHttpStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorResponse response = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
