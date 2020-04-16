package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:24
 * @Description: <描述>
 */

public class ProductRoomExistException extends Exception {

    public ProductRoomExistException() {
        super();
    }

    public ProductRoomExistException(String message) {
        super(message);
    }

    public ProductRoomExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductRoomExistException(Throwable cause) {
        super(cause);
    }

    protected ProductRoomExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
