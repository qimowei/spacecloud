package cn.nuaa.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:21
 * @Description: <描述>
 */
@Data
public class ProductSystem implements Serializable{
    private Integer id;
    private String sysType;
    private String sysVersion;
    private Integer status;
}
