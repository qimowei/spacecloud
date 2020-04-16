package cn.nuaa.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpc
 * @Date: 2020/2/10 14:27
 * @Description: <描述>
 */
@Data
public class ProductType implements Serializable{

    private Integer id;
    private String name;
    private Integer status;
    private Sort sort;
    private Integer sort_id;

    private ProductPrice productPrice;
    private ProductRoom productRoom;
    private ProductParam productParam;
}
