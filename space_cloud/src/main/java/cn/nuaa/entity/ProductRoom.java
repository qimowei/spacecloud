package cn.nuaa.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:21
 * @Description: <描述>
 */
@Data
public class ProductRoom implements Serializable{

    private Integer id;
    private String country;
    private String city;
    private ProductType productType;
    private Integer product_type_id;
}
