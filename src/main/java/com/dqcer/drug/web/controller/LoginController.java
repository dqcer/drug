package com.dqcer.drug.web.controller;

import com.dqcer.drug.web.service.UserService;
import com.dqcer.drug.web.vo.UserVo;
import com.dqcer.drug.web.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>Description:登录控制类</p>
 * @author administrator
 * @date 2018/3/29 19:20
 * @param
 * @return
 */
@Controller
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * 登录界面
     * @return
     */
    @GetMapping({"/toLogin","/",""})
    public String toLogin(){
        return "login";
    }

    /**
     * 异步判断用户名和密码是否正确
     * @param userVo
     * @param request
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    public Result ajaxLogin(UserVo userVo, HttpServletRequest request){
        printLog(request,userVo);
        return userService.findUser(userVo);
    }
    /**
     * 主界面
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request){
        printLog(request, model);
        return "index_v1";
    }
}
