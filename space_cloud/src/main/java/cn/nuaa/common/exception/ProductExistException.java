package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:24
 * @Description: <描述>
 */

public class ProductExistException extends Exception {

    public ProductExistException() {
        super();
    }

    public ProductExistException(String message) {
        super(message);
    }

    public ProductExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductExistException(Throwable cause) {
        super(cause);
    }

    protected ProductExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
