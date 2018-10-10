package com.sztech.springclouduser.handle;

import com.sztech.springcloudbean.expection.SpringCloudExpection;
import com.sztech.springcloudbean.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类，用以处理系统中抛出的异常
 * Created by zhongjunkai on 17/11/27.
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 捕获异常，返回Result对象，并将异常记录到日志中
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e){
        Result result = new Result();
        //可以根据不同的异常类型采取不同的处理方式
        if( e instanceof Exception){
            SpringCloudExpection exception = (SpringCloudExpection)e;
            result.setCode(exception.getCode());
            result.setMsg(exception.getMessage());
        }
        logger.error("【系统异常】: ", e);
        return result;
    }
}
