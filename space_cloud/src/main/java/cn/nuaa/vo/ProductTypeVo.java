package cn.nuaa.vo;

import cn.nuaa.entity.ProductParam;
import cn.nuaa.entity.ProductPrice;
import cn.nuaa.entity.ProductRoom;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpc
 * @Date: 2020/2/19 16:40
 * @Description: <描述>
 */
@Data
public class ProductTypeVo implements Serializable{
    private String ids;
    private Integer id;
    private String name;
    private Integer status;
    private Integer sort_id;

    private ProductPrice productPrice;
    private ProductRoom productRoom;
    private ProductParam productParam;
}
