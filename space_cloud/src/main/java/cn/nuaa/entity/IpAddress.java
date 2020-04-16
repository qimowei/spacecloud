package cn.nuaa.entity;

import java.util.Date;
import lombok.Data;
import java.io.Serializable;

/**
 * (IpAddress)实体类
 *
 * @author wpc
 * @since 2020-04-02 17:31:58
 */
@Data
public class IpAddress implements Serializable {
    private static final long serialVersionUID = -64165534409307048L;
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