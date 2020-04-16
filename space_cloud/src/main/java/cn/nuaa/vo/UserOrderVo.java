package cn.nuaa.vo;

import cn.nuaa.entity.ProductRoom;
import cn.nuaa.entity.ProductSystem;
import cn.nuaa.entity.ProductType;
import cn.nuaa.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserOrderVo)实体类
 *
 * @author wpc
 * @since 2020-03-28 19:43:12
 */
@Data
public class UserOrderVo implements Serializable {
    /**
    * 订单id
    */
    private Long id;
    /**
    * 订单状态
    */
    private Integer orderStatus;
    /**
    * 商品名称id
    */
    private Integer productTypeId;
    /**
    * 操作系统id
    */
    private Integer productSystemId;
    /**
    * 消费时间
    */
    private Date orderDate;
    /**
    * 购买总价
    */
    private Double orderPrice;
    /**
    * 购买期限/月
    */
    private Integer orderTerm;
    /**
    * 备注
    */
    private String remark;
    /**
    * 用户登录名
    */
    private String userAccount;

    /**
     * 创建订单时间
     */
    private Date createDate;

    /**
     * 商品的名称，用于搜索提供参数用的
     */
    private String productTypeName;

    /**
     * productType的实例
     */
    private ProductType productType;
    /**
     * productSystem的实例
     */
    private ProductSystem productSystem;

    /**
     * productRoom的实例
     */
    private ProductRoom productRoom;
}