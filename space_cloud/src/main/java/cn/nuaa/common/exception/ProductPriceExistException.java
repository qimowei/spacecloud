package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:24
 * @Description: <描述>
 */

public class ProductPriceExistException extends Exception {

    public ProductPriceExistException() {
        super();
    }

    public ProductPriceExistException(String message) {
        super(message);
    }

    public ProductPriceExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductPriceExistException(Throwable cause) {
        super(cause);
    }

    protected ProductPriceExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
