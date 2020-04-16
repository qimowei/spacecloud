package cn.nuaa.service;

import cn.nuaa.entity.UserBusiness;

import java.util.List;

/**
 * (UserBusiness)表服务接口
 *
 * @author wpc
 * @since 2020-03-28 22:34:41
 */
public interface UserBusinessService {

    /**
     * 查询所有，用于表格
     * @param userBusiness
     * @return
     */
    List<UserBusiness> findAll(UserBusiness userBusiness);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserBusiness findById(Long id);

    /**
     * 新增数据
     *
     * @param userBusiness 实例对象
     * @return 实例对象
     */
    UserBusiness add(UserBusiness userBusiness);

    /**
     * 修改数据
     *
     * @param userBusiness 实例对象
     * @return 实例对象
     */
    UserBusiness modify(UserBusiness userBusiness);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id);
}