package cn.nuaa.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wpc
 * @Date: 2020/3/5 17:53
 * @Description: <描述>
 */
@Data
public class AdminVo implements Serializable{

    private Integer id;
    private String name;
    private String adminAccount;
    private String password;
    private String phone;
    private String address;
    private String email;
    private Date createDate;
    /**
     * 邀请码
     */
    private String invitationCode;
}
