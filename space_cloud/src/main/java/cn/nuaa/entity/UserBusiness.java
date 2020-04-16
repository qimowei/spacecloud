package cn.nuaa.entity;

import java.util.Date;
import lombok.Data;
import java.io.Serializable;

/**
 * (UserBusiness)实体类
 *
 * @author wpc
 * @since 2020-03-28 22:34:41
 */
@Data
public class UserBusiness implements Serializable {
    private static final long serialVersionUID = 899584979225030144L;
    /**
    * 交易记录id
    */
    private Long id;
    /**
    * 订单id，链接order表或者自定义
    */
    private Long orderId;
    /**
     * 商品名称id
     */
    private Integer productTypeId;
    /**
    * 交易类型
    */
    private String buyType;
    /**
    * 交易金额
    */
    private Double buyMoney;
    /**
    * 备注
    */
    private String remark;
    /**
    * 交易时间
    */
    private Date buyDate;
    /**
     * 用户登录名
     */
    private String userAccount;
    /**
    *  userOrder的实例
    */
    private UserOrder userOrder;
    /**
     * productType的实例
     */
    private ProductType productType;

}