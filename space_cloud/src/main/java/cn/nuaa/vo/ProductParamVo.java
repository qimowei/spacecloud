package cn.nuaa.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpc
 * @Date: 2020/3/2 17:45
 * @Description: <描述>
 */
@Data
public class ProductParamVo implements Serializable{

    private String ids;
    private Integer id;
    /**
     * cpu型号
     */
    private String cputype;
    /**
     * cpu核数/核
     */
    private Integer cpu;
    /**
     * 内存大小-int型/GB
     */
    private Integer ram;
    /**
     * 带宽/Mbps
     */
    private Integer bandwidth;
    /**
     * 系统盘/GB
     */
    private Integer sysdisk;
    /**
     * 数据盘/GB
     */
    private Integer datadisk;
    /**
     * 流量/GB
     */
    private Integer traffic;
    /**
     * 数据库大小/MB
     */
    private Integer sql_size;
    private Integer product_type_id;
}
