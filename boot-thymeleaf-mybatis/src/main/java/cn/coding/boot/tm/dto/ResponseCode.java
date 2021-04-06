package cn.coding.boot.tm.dto;

import cn.coding.boot.tm.enums.ResponseEnums;
import lombok.Data;

/**
 * 作为返回请求响应方法，将返回信心封装后返回给前端
 */
@Data
public class ResponseCode {
    private Integer code;
    private String msg;
    private Object data;

    public ResponseCode() {
    }

    public ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResponseCode(ResponseEnums enums) {
        this.code = enums.getCode();
        this.msg = enums.getInfo();
    }
    public ResponseCode(ResponseEnums enums, Object data) {
        this.code = enums.getCode();
        this.msg = enums.getInfo();
        this.data = data;
    }

    public static ResponseCode success() {
        return new ResponseCode(ResponseEnums.SUCCESS);
    }
    public static ResponseCode success(Object data) {
        return new ResponseCode(ResponseEnums.SUCCESS, data);
    }
    public static ResponseCode error() {
        return new ResponseCode(ResponseEnums.SYSTEM_ERROR);
    }
}
