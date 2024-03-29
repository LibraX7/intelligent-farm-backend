package com.sipc.intelligentfarmbackend.exception;


import com.sipc.intelligentfarmbackend.pojo.model.res.BlankRes;
import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResult<BlankRes> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        return CommonResult.fail(msg);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public CommonResult<BlankRes> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return CommonResult.fail("输入格式有误！");
    }


    @ExceptionHandler({SQLException.class})
    public CommonResult<BlankRes> handleSQLException(SQLException e) {
        e.printStackTrace();
        return CommonResult.fail("SQL异常");
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public CommonResult<BlankRes> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ex.printStackTrace();
        return CommonResult.fail("数据完整性异常");
    }
}
