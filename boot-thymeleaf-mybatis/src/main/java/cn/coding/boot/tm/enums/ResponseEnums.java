package cn.coding.boot.tm.enums;

import lombok.Getter;

/**
 * 枚举类，用于提示用户操作结果
 */
public enum ResponseEnums {
    SUCCESS(200, "操作成功"),
    ACCOUNT_UNKNOWN(500, "账户不存在"),
    ACCOUNT_ERROR(500, "用户或密码错误"),
    SYSTEM_ERROR(500, "系统错误"),
    PARM_ERROR(400, "参数错误"),
    PARM_REPEAT(400, "参数错误已存在"),
    OTHER(-100, "其他错误");


    @Getter
    private int code;
    @Getter
    private String info;

    ResponseEnums(int code, String info) {
        this.code = code;
        this.info = info;
    }
}
