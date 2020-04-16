package cn.nuaa.entity;

import java.util.Date;
import lombok.Data;
import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author wpc
 * @since 2020-04-02 17:36:03
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 788326581970540696L;
    
    private Long id;
    /**
    * ip地址表的id
    */
    private Integer ipAddressId;
    /**
    * order的id
    */
    private Long orderId;
    /**
     * 产品状态,1代表正常，0代表过期
     */
    private Integer productStatus;
    /**
    * 开始时间
    */
    private Date startDate;
    /**
     * 到期时间
     */
    private Date endDate;
    /**
     * 产品买了多少个月
     */
    private Integer orderTerm;
    /**
     * 距离傲到期天数还剩多少天
     */
    private Integer dateNum;
    /**
    * 大分类sort的id
    */
    private Integer sortId;
    /**
    * 用户登录名
    */
    private String userAccount;
    /**
    * 创建时间
    */
    private Date createDate;
    /**
    * IpAddress的实例化
    */
    private IpAddress ipAddress;
    /**
    * userOrder的实例化
    */
    private UserOrder userOrder;
    /**
    * sort的实例化
    */
    private Sort sort;

    /**
     * productRoom的实例
     */
    private ProductRoom productRoom;

    /**
     * productType的实例
     */
    private ProductType productType;
    /**
     * productSystem的实例
     */
    private ProductSystem productSystem;

    /**
     * productParam的实例
     */
    private ProductParam productParam;

}