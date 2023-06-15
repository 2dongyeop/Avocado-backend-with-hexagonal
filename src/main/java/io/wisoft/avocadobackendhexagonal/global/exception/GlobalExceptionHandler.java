package io.wisoft.avocadobackendhexagonal.global.exception;

import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import io.wisoft.avocadobackendhexagonal.global.exception.ErrorResponse;
import io.wisoft.avocadobackendhexagonal.global.exception.duplicate.DuplicateEmailException;
import io.wisoft.avocadobackendhexagonal.global.exception.duplicate.DuplicateHospitalException;
import io.wisoft.avocadobackendhexagonal.global.exception.notfound.CustomNotFoundException;
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

import java.net.BindException;
import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 엔티티 조회 실패시
     */
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final CustomNotFoundException exception) {

        log.error("handlerNotFoundException", exception);
        return getResponse(exception.getErrorCode());
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
