package cn.nuaa.entity;

import java.util.Date;
import lombok.Data;

import java.io.Serializable;

/**
 * (UserOrder)实体类
 *
 * @author wpc
 * @since 2020-03-28 19:43:12
 */
@Data
public class UserOrder implements Serializable {
    private static final long serialVersionUID = -99410950866058281L;
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
    * 付款时间
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
    * productType的实例
    */
    private ProductType productType;
    /**
    * productSystem的实例
    */
    private ProductSystem productSystem;
}