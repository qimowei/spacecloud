package cn.nuaa.vo;

import cn.nuaa.entity.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (ProductVo)实体类
 *
 * @author wpc
 * @since 2020-04-02 17:36:03
 */
@Data
public class ProductVo implements Serializable {

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
    * 大分类sort的id
    */
    private Integer sortId;
    /**
     * 大分类sort的名称,用于分类
     */
    private String sortName;
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

    /**
     * 搜索用的时间
     */
    private String searchDate;

}