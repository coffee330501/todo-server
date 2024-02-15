package io.github.coffee330501.advice;

import io.github.coffee330501.base.R;
import io.github.coffee330501.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionInterceptor {
    @ExceptionHandler(CustomException.class)
    public R handle(CustomException e) {
        e.printStackTrace();
        return R.err(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R handle(Exception e) {
        e.printStackTrace();
        return R.err("系统异常");
    }
}
