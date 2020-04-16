package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:24
 * @Description: <描述>
 */

public class AdminExistException extends Exception {

    public AdminExistException() {
        super();
    }

    public AdminExistException(String message) {
        super(message);
    }

    public AdminExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminExistException(Throwable cause) {
        super(cause);
    }

    protected AdminExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
