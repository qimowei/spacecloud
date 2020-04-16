package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:24
 * @Description: <描述>
 */

public class UserOrderExistException extends Exception {

    public UserOrderExistException() {
        super();
    }

    public UserOrderExistException(String message) {
        super(message);
    }

    public UserOrderExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserOrderExistException(Throwable cause) {
        super(cause);
    }

    protected UserOrderExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
