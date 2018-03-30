package com.dqcer.drug.config;

import com.dqcer.drug.web.exception.BusinessException;
import com.dqcer.drug.web.exception.SystemError;
import com.dqcer.drug.web.vo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description:通过使用spring的异常注解@Exception来统一异常处理类</p>
 * @author administrator
 * @date 2018/3/30 11:34
 * @param
 * @return
 */
@ControllerAdvice
@Controller
public class GlobalExceptionHandler implements ErrorController{

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @ExceptionHandler(value = Exception.class)
    @RequestMapping("/error")
    public Result defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        logger.error("", ex);
        String result = "";
        if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            for (FieldError fieldErro : bindException.getBindingResult().getFieldErrors()) {
                //result += "【" + fieldErro.getField() + ":" + fieldErro.getDefaultMessage() + "】";
                result += "【"+fieldErro.getDefaultMessage() + "】";
            }
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            for (FieldError fieldErro : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
                //result += "【" + fieldErro.getField() + ":" + fieldErro.getDefaultMessage() + "】";
                result += "【" + fieldErro.getDefaultMessage() + "】";
            }
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException = (HttpRequestMethodNotSupportedException) ex;
            result = httpRequestMethodNotSupportedException.getMessage();
        } else if (ex instanceof NoHandlerFoundException) {
            result = "无效的请求地址";
        } else if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException missingServletRequestParameterException = (MissingServletRequestParameterException) ex;
            result = "缺少必要的参数[" + missingServletRequestParameterException.getMessage() + "]";
        } else if(ex instanceof BusinessException){
            BusinessException be = (BusinessException)ex;
            return new Result(be.getErrorCode(), be.getErrorMsg());
        } else if(ex.getCause() instanceof NumberFormatException){
            NumberFormatException be = (NumberFormatException)ex.getCause();
            result = "参数数据类型错误：[" + be.getMessage() + "]，请检查手动输入的参数是否符合规范";
            Result re = new Result(SystemError.SYSTEM_ERROR.getCode(), result);
            re.setContent(ex);
            return re;
        } else {
            result = "Error handling OR Service unavaliable";
            Result re = new Result(SystemError.SYSTEM_ERROR.getCode(), result);
            re.setContent(ex);
            return re;
        }
        return new Result(SystemError.SYSTEM_ERROR.getCode(), result);
    }
}
