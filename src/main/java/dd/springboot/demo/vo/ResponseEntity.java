package dd.springboot.demo.vo;

import lombok.Data;
import org.yky.common.exception.ErrorType;

/**
 * router response VO.
 *
 * @author shiquan.zhuang
 * @date 2021-04-08 17:08:00
 */
@Data
public class ResponseEntity<T> {
    public static final String RET_STATUS_SUCCESS = "S";
    public static final String RET_STATUS_FAIL = "F";
    public static final String RET_STATUS_PROCESSING = "O";
    public static final String RET_CODE_SUCCESS = "0";
    public static final String RET_MSG_SUCCESS = "success";


    /**
     * 交易状态;S：成功 F：失败.
     */
    private String status;

    /**
     * 返回代码.
     */
    private String code;

    /**
     * 返回信息.
     */
    private String msg;


    private T data;

    public ResponseEntity() {

    }

    public static <T> ResponseEntity<T> ok(T data) {
        ResponseEntity vo = new ResponseEntity();
        vo.setStatus(RET_STATUS_SUCCESS);
        vo.setCode(RET_CODE_SUCCESS);
        vo.setMsg(RET_MSG_SUCCESS);
        vo.setData(data);
        return vo;
    }

    public static <T> ResponseEntity<T> ok() {
        ResponseEntity vo = new ResponseEntity();
        vo.setStatus(RET_STATUS_SUCCESS);
        vo.setCode(RET_CODE_SUCCESS);
        vo.setMsg(RET_MSG_SUCCESS);
        return vo;
    }

    public static ResponseEntity failed(String code, String msg) {
        ResponseEntity vo = new ResponseEntity();
        vo.setStatus(RET_STATUS_PROCESSING);
        vo.setCode(code);
        vo.setMsg(msg);
        return vo;
    }

    public static ResponseEntity failed(ErrorType errorType) {
        ResponseEntity vo = new ResponseEntity();
        vo.setStatus(RET_STATUS_PROCESSING);
        vo.setCode(errorType.getCode());
        vo.setMsg(errorType.getMsg());
        return vo;
    }

    public static ResponseEntity failed(ErrorType errorType, String msg) {
        ResponseEntity vo = new ResponseEntity();
        vo.setStatus(RET_STATUS_PROCESSING);
        vo.setCode(errorType.getCode());
        vo.setMsg(msg);
        return vo;
    }

    public boolean checkSuccess() {
        return RET_CODE_SUCCESS.equalsIgnoreCase(this.code);
    }

    public boolean checkSuccessWithData() {
        if (null == data) {
            return false;
        }
        return RET_CODE_SUCCESS.equalsIgnoreCase(this.code);
    }
}
