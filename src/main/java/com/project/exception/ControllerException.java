package com.project.exception;

import com.project.common.ErrorCode;
import com.project.util.ResultUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-09-14-17:48
 * @version:
 */
@ControllerAdvice
public class ControllerException {
    private final static String EXCEPTION_MSG_KEY = "Exception message : ";
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidException(MethodArgumentNotValidException e) {
        //日志记录错误信息
        // log.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        //将错误信息返回给前台
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }
}
