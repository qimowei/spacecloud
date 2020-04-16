package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/2/19 18:20
 * @Description: <描述>
 */

public class SortExistException extends Exception {
    public SortExistException() {
        super();
    }

    public SortExistException(String message) {
        super(message);
    }

    public SortExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SortExistException(Throwable cause) {
        super(cause);
    }

    protected SortExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
