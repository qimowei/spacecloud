package cn.nuaa.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author wpc
 * @since 2020-03-23 21:27:31
 */
@Data
public class User implements Serializable {
    /**
    * 用户信息id
    */
    private Integer id;
    /**
    * 用户昵称
    */
    private String name;
    /**
    * 用户登陆账号
    */
    private String userAccount;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 用户身份证
    */
    private String identity;
    /**
    * QQ
    */
    private String qqNum;
    /**
    * 用户手机号
    */
    private String phone;
    /**
     * 用户邮箱
     */
    private String email;
    /**
    * 用户所在城市
    */
    private String city;
    /**
    * 用户具体地址
    */
    private String address;
    /**
    * 性别
    */
    private Integer sex;
    /**
    * 个性签名
    */
    private String signature;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 用户余额
     */
    private Double balance;

    /**
     * 用户消费金额
     */
    private Double allPay;

    /**
     * 最后交易时间
     */
    private Date endpayDate;
}