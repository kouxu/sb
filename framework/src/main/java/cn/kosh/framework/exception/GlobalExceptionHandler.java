package cn.kosh.framework.exception;

import cn.kosh.framework.domain.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by kosh on 2017/5/5.
 */
//@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleThrowable(Throwable th) {
        logger.error(th.getLocalizedMessage(), th);
        ErrorMessage msg = new ErrorMessage();
        if (th instanceof BaseException) {
            BaseException be = (BaseException) th;
            msg.setCode(be.getCode());
        } else {
            msg.setCode(500);
        }
        msg.setMessage(th.getMessage());
        msg.setDeveloperMessage(th.toString());
        msg.setMoreInfo("...");
        return msg;
    }

    private String getStackTrace(Throwable th) {
        ByteArrayOutputStream buf = null;
        try {
            buf = new ByteArrayOutputStream();
            th.printStackTrace(new java.io.PrintWriter(buf, true));
            return buf.toString();
        } finally {
            try {
                buf.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
