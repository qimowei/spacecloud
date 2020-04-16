package cn.nuaa.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (IpAddressVo)实体类
 *
 * @author wpc
 * @since 2020-04-02 17:31:58
 */
@Data
public class IpAddressVo implements Serializable {
    /**
    * IP地址的ip
    */
    private Integer id;
    /**
    * 公网ip
    */
    private String publicIp;
    /**
    * ip地址所在国家
    */
    private String country;
    /**
    * ip地址的登录账号
    */
    private String ipLogin;
    /**
    * ip地址的登录密码
    */
    private String ipPassword;
    /**
    * ip地址的状态
    */
    private Integer ipStatus;
    /**
    * 创建时间
    */
    private Date createDate;

}