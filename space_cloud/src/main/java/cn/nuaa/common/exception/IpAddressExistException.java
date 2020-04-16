package cn.nuaa.common.exception;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:24
 * @Description: <描述>
 */

public class IpAddressExistException extends Exception {

    public IpAddressExistException() {
        super();
    }

    public IpAddressExistException(String message) {
        super(message);
    }

    public IpAddressExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public IpAddressExistException(Throwable cause) {
        super(cause);
    }

    protected IpAddressExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
