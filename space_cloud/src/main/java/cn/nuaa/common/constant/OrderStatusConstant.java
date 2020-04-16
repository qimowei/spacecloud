package cn.nuaa.common.constant;

/**
 * @Author: wpc
 * @Date: 2020/2/10 19:34
 * @Description: <描述>
 */

public interface OrderStatusConstant {

    /**
     * 订单状态，0代表交易成功，已付款
     */
    int ORDER_STATUS_SUCCESS=0;


    /**
     * 订单状态，1代表创建订单未付款，可取消订单
     */
    int ORDER_STATUS_FAIL=1;
}
