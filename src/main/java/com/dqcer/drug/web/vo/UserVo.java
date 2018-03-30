package com.dqcer.drug.web.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>Description:用户参数类</p>
 * @author dq
 * @date 2018/3/30 11:16
 * @param
 * @return
 */
public class UserVo implements Serializable{

    private static final long serialVersionUID = 1255133969717204609L;

    @NotNull(message = "登录用户名不能为空")
    @Size(min = 2, max = 64, message = "登录用户名只能在2-64个字符之内")
    private String username;

    @NotNull(message = "登录密码不能为空")
    @Size(min = 5, max = 64, message = "登录密码只能在5-64个字符之内")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
