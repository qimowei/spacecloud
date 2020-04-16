package cn.nuaa.vo;

import cn.nuaa.entity.ProductType;
import cn.nuaa.entity.User;
import cn.nuaa.entity.UserOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserBusinessVo)实体类
 *
 * @author wpc
 * @since 2020-03-28 22:34:41
 */
@Data
public class UserBusinessVo implements Serializable {
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

}