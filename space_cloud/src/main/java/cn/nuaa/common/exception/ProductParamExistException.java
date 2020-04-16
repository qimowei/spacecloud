package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/3/2 18:20
 * @Description: <描述>
 */

public class ProductParamExistException extends Exception {
    public ProductParamExistException() {
        super();
    }

    public ProductParamExistException(String message) {
        super(message);
    }

    public ProductParamExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductParamExistException(Throwable cause) {
        super(cause);
    }

    protected ProductParamExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
