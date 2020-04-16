package cn.nuaa.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:21
 * @Description: <描述>
 */
@Data
public class ProductPrice implements Serializable{
    private Integer id;
    private Double price;
    private ProductType productType;
    private Integer product_type_id;
}
