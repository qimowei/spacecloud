package cn.nuaa.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wpc
 * @Date: 2020/3/23 21:29
 * @Description: <描述>
 */
@Data
public class UserVo implements Serializable {
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
    @NotNull(message = "登录名不能为空")
    private String userAccount;
    /**
     * 注册时的密码
     */
    private String password;
    /**
     * 用户旧密码
     */
    private String old_password;
    /**
     * 用户新密码
     */
    private String new_password;
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
     * 用户所在省份
     */
    private String province;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 用户所在区
     */
    private String county;
    /**
     * 用户具体地址
     */
    private String address;
    /**
     * 性别 ,0男，1女，2保密
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

    /**
     * 用户充值的金额
     */
    @NotNull(message = "充值金额不能为空")
    private Double rechargeNum;
}
