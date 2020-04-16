package cn.nuaa.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wpc
 * @Date: 2020/3/5 17:36
 * @Description: <描述>
 */
@Data
public class Admin implements Serializable {

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
    //@Value("${admin.invitationCode}")
    //private String invitationCode;

}
