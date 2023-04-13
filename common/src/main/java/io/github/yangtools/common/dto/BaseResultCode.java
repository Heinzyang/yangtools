package io.github.yangtools.common.dto;

/**
 * @author dylanMa
 * @description 基础结果类
 * @createTime 2023-04-10
 */
public enum BaseResultCode implements ResultCode {

    SUCCESS(10000, "成功"),
    PARAM_ERROR(10001, "参数错误"),
    OPERATION_FAIL(10002, "操作失败"),

    ;

    private int code;
    private String message;

    BaseResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
