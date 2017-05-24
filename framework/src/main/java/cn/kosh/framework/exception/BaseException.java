package cn.kosh.framework.exception;

/**
 * Created by kosh on 2017/5/18.
 */
public class BaseException extends RuntimeException {
    private int code;

    public BaseException(String message, Throwable th) {
        this(500, message, th);
    }

    public BaseException(int code, String message, Throwable th) {
        super(message, th);
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
