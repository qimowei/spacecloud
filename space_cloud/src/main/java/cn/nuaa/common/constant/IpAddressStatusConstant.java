package cn.nuaa.common.constant;

/**
 * @Author: wpc
 * @Date: 2020/2/10 19:34
 * @Description: <描述>
 */

public interface IpAddressStatusConstant {

    /**
     * ip地址处于关闭状态，不接受使用
     */
    int IPADDRESS_CLOSE_STATUS=0;


    /**
     * ip地址已经有人在使用
     */
    int IPADDRESS_ON_STATUS=1;

    /**
     * ip地址开启且无人在使用
     */
    int IPADDRESS_NO_STATUS=2;
}
