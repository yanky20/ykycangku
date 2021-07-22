package org.yky.common.exception;

import lombok.Getter;

/**
 * 系统错误码
 * 3-系统错误，由与操作系统交互产生的异常，如网络错误、磁盘异常、内存不足等，此类错误支持调用方稍后重试，待系统恢复后可以恢复。
 * 4-依赖错误，由系统所依赖的底层服务报错导致，如网db、redis异常等，此类错误支持调用方稍后重试，待系统恢复后可以恢复。
 */
@Getter
public enum BizErrorType implements ErrorType {

    INVALID_PARAM(3120001, "illegal request parameter"),

    UNKNOW_TYPE(4120005, "unknown type error"),

    EMPTY_KAFKA_MESSAGE(4120012, "kafka message is empty"),

    IBM_MQ_NAK(990001, "send message to ibmmq failed"),

    // sign on/off echo
    SIGN_ON_RESP_ERROR(500001, "sign on resp is not correct"),
    SIGN_OFF_RESP_ERROR(500002, "sign on resp is not correct"),
    ECHO_RESP_ERROR(500003, "echo resp is not correct"),

    ERROR_FAST_NOTIFICATION(600001, "error fast notification")

    ;

    private String code;
    private String msg;

    BizErrorType(int code, String msg) {
        this.code = String.valueOf(code);
        this.msg = msg;
    }

    BizErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
