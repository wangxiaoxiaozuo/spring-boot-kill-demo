package com.web.config.exception;

import com.common.data.entity.BizException;
import com.common.data.entity.ErrorResult;
import com.common.data.enums.ErrorResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

/**
 * 全局异常处理类
 *
 * @author wangjian
 * @date 2020/4/17 13:03
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAutoConfiguration {

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult bizExceptionHandler(BizException e) {
        log.info("业务异常: 异常代码【{}】, 异常信息: {}", e.getCode(), e.getMessage());
        return new ErrorResult().setErrorCode(e.getCode()).setErrorMessage(e.getMessage());
    }

    @ExceptionHandler({
        ServletRequestBindingException.class,
        HttpMessageNotReadableException.class,
        MethodArgumentTypeMismatchException.class
    })

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult badRequestExceptionHandler(Exception e) {
        log.info("参数错误异常: {}", e.getMessage());
        return ErrorResultEnum.convertToErrorResult(ErrorResultEnum.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResult methodNotAllowedExceptionHandler() {
        return ErrorResultEnum.convertToErrorResult(ErrorResultEnum.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult badParamsRequestHandler(Exception e) {
        String message = "参数校验【未知错误】";
        if (e.getClass().isAssignableFrom(ConstraintViolationException.class)) {
            message = ((ConstraintViolationException) e).getConstraintViolations().iterator().next()
                .getMessage();
        } else if (e.getClass().isAssignableFrom(MethodArgumentNotValidException.class)) {
            message = Objects.requireNonNull(
                ((MethodArgumentNotValidException) e).getBindingResult().getFieldError())
                .getDefaultMessage();
        }
        log.info("参数校验失败: {}", message);
        return ErrorResultEnum.convertToErrorResult(ErrorResultEnum.BAD_PARAMS_REQUEST)
            .setErrorMessage(message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult serverExceptionHandler(Exception e) {
        log.error("服务器未知异常: {}", e.getMessage(), e);
        return ErrorResultEnum.convertToErrorResult(ErrorResultEnum.INTERNAL_SERVER_ERROR);
    }

}
