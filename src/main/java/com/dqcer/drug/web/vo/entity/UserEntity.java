package com.dqcer.drug.web.vo.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>Description:用户实体类</p>
 * @author administrator
 * @date 2018/3/30 9:19
 * @param
 * @return
 */
public class UserEntity implements Serializable{

    private static final long serialVersionUID = -5739631537118828414L;

    /**
     * 系统管理员
     */
    public static final int ROLE_SYS = 0;

    /**
     * 仓库管理员
     */
    public static final int ROLE_WAREHOUSE = 1;

    /**
     * 药柜管理员
     */
    public static final int ROLE_CABINET = 2;

    /**
     * 前台人员
     */
    public static final int ROLE_RECEPTION = 3;

    /**
     * 代煎人员
     */
    public static final int ROLE_DECOCTION = 4;

    /**
     * 主键
     */
    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    /**
     * 用户名
     */
    @NotNull(message = "登录用户名不能为空")
    @Size(min = 2, max = 64, message = "登录用户名只能在2-64个字符之内")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "登录密码不能为空")
    @Size(min = 5, max = 64, message = "登录密码只能在5-64个字符之内")
    private String password;

    /**
     * 权限 默认仓库管理员
     */
    @NotNull(message = "用户权限不能为空")
    private Integer role = ROLE_WAREHOUSE;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
