package cn.nuaa.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:30
 * @Description: <描述>
 */
@Data
public class ProductRoomVo implements Serializable{

    private String ids;
    private Integer id;
    private String country;
    private String city;
    private Integer product_type_id;
}
