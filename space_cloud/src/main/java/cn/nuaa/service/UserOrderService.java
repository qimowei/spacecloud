package cn.nuaa.service;

import cn.nuaa.common.exception.UserOrderExistException;
import cn.nuaa.entity.UserOrder;
import cn.nuaa.vo.UserOrderVo;

import java.util.List;

/**
 * (UserOrder)表服务接口
 *
 * @author wpc
 * @since 2020-03-28 19:43:12
 */
public interface UserOrderService {

    /**
     * 查询所有，用于表格
     * @param userOrderVo
     * @return
     */
    List<UserOrder> findAll(UserOrderVo userOrderVo);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserOrderVo queryById(Long id);


    /**
     * 新增数据
     *
     * @param userOrderVo 实例对象
     * @return 实例对象
     */
    UserOrder add(UserOrderVo userOrderVo) throws UserOrderExistException;

    /**
     * 修改数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    UserOrderVo update(UserOrder userOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id) throws UserOrderExistException;

    /**
     * 根据用户名查询订单状态
     * @param userAccount
     * @return
     */
    List<UserOrder> findOrderStatusByUserAccount(String userAccount);

    /**
     * 支付接口
     * @param userAccount
     * @param orderId
     */
    void pay(String userAccount, Long orderId) throws UserOrderExistException;
}