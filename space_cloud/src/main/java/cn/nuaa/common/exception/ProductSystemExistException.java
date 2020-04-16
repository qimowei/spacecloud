package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:24
 * @Description: <描述>
 */

public class ProductSystemExistException extends Exception {

    public ProductSystemExistException() {
        super();
    }

    public ProductSystemExistException(String message) {
        super(message);
    }

    public ProductSystemExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductSystemExistException(Throwable cause) {
        super(cause);
    }

    protected ProductSystemExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
