package org.yky.common.exception;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class BaseException extends RuntimeException {

    /**
     * 异常对应的错误类型
     */
    private final ErrorType errorType;

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    public BaseException(String errorCode, String msg) {
        this.errorType = new ErrorType() {
            @Override
            public String getCode() {
                return errorCode;
            }

            @Override
            public String getMsg() {
                return msg;
            }
        };
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String getMessage() {
        return "[" + errorType.getCode() + "]"
                + Joiner.on(",").skipNulls().join(Lists.newArrayList(errorType.getMsg(), super.getMessage()));
    }

}
