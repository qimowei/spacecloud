package cn.nuaa.vo;

import cn.nuaa.entity.ProductType;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:30
 * @Description: <描述>
 */
@Data
public class ProductPriceVo implements Serializable{
    private String ids;
    private Integer id;
    private Double price;
    private Integer product_type_id;
}
