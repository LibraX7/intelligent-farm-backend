package com.sipc.intelligentfarmbackend.exception;

import com.sipc.intelligentfarmbackend.common.ResultEnum;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ResultEnum code;

    public BaseException(String message) {
        super(message);
        this.code = ResultEnum.FAILED;
    }

    public BaseException(ResultEnum code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultEnum.FAILED;
    }

    public BaseException(ResultEnum code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}