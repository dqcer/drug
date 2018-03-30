package com.dqcer.drug.web.exception;


/**
 * <p>Description:不同的管理员间code应该分段处理</p>
 * @author administrator
 * @date 2018/3/29 21:44
 * @param
 * @return
 */
public enum SystemError {

    //登录认证相关
    INVALID_USERNAME_OR_PASSWORD	("010001","登录信息错误"),
    INVALID_AUTH_INFO				("010001","错误的认证信息或登录超时，请重新登录"),
    SYSTEM_ERROR                    ("000001", "服务器内部错误，请稍后再试");

    private String  code;

    private String message;

    private SystemError(String code,String messge) {
        this.code = code;
        this.message = messge;
   }

    public static SystemError getByCode(String code) {
        for (SystemError errorCode : SystemError.values()) {
            if (code.equals(errorCode.getCode())) {
                return errorCode;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public SystemError setMessage(String message) {
        this.message = message;
        return this;
    }
}
