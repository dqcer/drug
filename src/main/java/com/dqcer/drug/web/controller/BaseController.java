package com.dqcer.drug.web.controller;

import com.alibaba.fastjson.JSON;
import com.dqcer.drug.web.exception.SystemError;
import com.dqcer.drug.web.vo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description:控制层基类</p>
 * @author administrator
 * @date 2018/3/29 19:21
 * @param
 * @return
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    Result result = new Result();

    /**
     * 成功 -无返回结果
     * @return
     */
    protected Result success(){
        return new Result();
    }

    /**
     * 成功 -又返回结果
     * @param content
     * @return
     */
    protected Result success(Object content){
        return result.setContent(content);
    }

    /**
     * 失败
     * @param code
     * @param message
     * @return
     */
    protected Result error(String code,String message){
        return new Result(code,message, Result.Status.ERROR);
    }

    protected Result error(SystemError error) {
        return error(error.getCode(), error.getMessage());
    }

    private static final ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

    @ModelAttribute
    public void setRequest(HttpServletRequest rquest) {
        requestLocal.remove();
        requestLocal.set(rquest);
    }

    /**
     * 打印日志信息
     */
    public void printLog(HttpServletRequest request, Object param) {
        logger.info("request uri:{}, request param:{}", request.getRequestURI(), JSON.toJSONString(param));
    }

    /**
     * 打印请求的参数
     */
    public void printRequestParam(Object param) {
        logger.info("request uri:{}, request param:{}", requestLocal.get().getRequestURI(), JSON.toJSONString(param));
    }
}
