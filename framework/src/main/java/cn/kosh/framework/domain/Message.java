package cn.kosh.framework.domain;

/**
 * Created by kosh on 2017/5/6.
 */
public class Message {
    private boolean success = true;
    private String message;
    private Object data;

    public Message() {
    }

    public Message(boolean success) {
        this(success, null);
    }

    public Message(boolean success, String message) {
        this(success, message, null);
    }

    public Message(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Message(Object data) {
        this(true, null, data);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
