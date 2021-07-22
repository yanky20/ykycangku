package org.yky.util.handler;



import dd.springboot.demo.vo.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.yky.common.exception.BaseException;
import org.yky.common.exception.BizErrorType;
import org.yky.common.exception.BizException;
import org.yky.common.exception.SystemErrorType;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class OtherEndPointExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity baseException(BaseException e) {
        log.error("", e);
        return ResponseEntity.failed(e.getErrorType());
    }

    @ExceptionHandler(value = {BizException.class})
    public ResponseEntity bizException(BizException e) {
        log.error("", e);
        return ResponseEntity.failed(e.getErrorType());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity validException(MethodArgumentNotValidException e) {
        FieldError fieldErr = (FieldError) e.getBindingResult().getAllErrors().get(0);
        //String msg = UpperSnakeCaseStrategy.toUpperSnake(fieldErr.getField()) + " " + fieldErr.getDefaultMessage();
        String msg = fieldErr.getField() + " " + fieldErr.getDefaultMessage();

        return ResponseEntity.failed(BizErrorType.INVALID_PARAM, msg);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity exception(Exception e) {
        log.error("", e);
        return ResponseEntity.failed(SystemErrorType.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity throwable(Throwable throwable) {
        log.error("", throwable);
        return ResponseEntity.failed(SystemErrorType.SYSTEM_ERROR);
    }

}
