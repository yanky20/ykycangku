package org.yky.common.exception;

import lombok.Getter;

@Getter
public enum SystemErrorType implements ErrorType {

    SYSTEM_ERROR(1120001, "系统异常"),
    
    SYSTEM_BUSY(1120002, "系统繁忙,请稍候再试"),

    ERROR_FAST_SYSTEM_ERROR(1120003, "message format error"),
    ERROR_FAST_FORMAT_TYPE_ERROR(1120004, "message type 初始化失败"),

    ;

    private String code;
    private String msg;

    SystemErrorType(int code, String msg) {
        this.code = String.valueOf(code);
        this.msg = msg;
    }

    SystemErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
