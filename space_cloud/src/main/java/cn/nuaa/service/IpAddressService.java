package cn.nuaa.service;

import cn.nuaa.common.exception.IpAddressExistException;
import cn.nuaa.entity.IpAddress;
import cn.nuaa.vo.IpAddressVo;
import java.util.List;

/**
 * (IpAddress)表服务接口
 *
 * @author wpc
 * @since 2020-04-02 17:31:58
 */
public interface IpAddressService {
    /**
     * 查询所有
     * @param ipAddressVo
     * @return
     */
    List<IpAddress> findAll(IpAddressVo ipAddressVo);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    IpAddress findById(Integer id);

    /**
     * 新增数据
     *
     * @param ipAddressVo 实例对象
     * @return 实例对象
     */
    IpAddress add(IpAddressVo ipAddressVo) throws IpAddressExistException;

    /**
     * 修改数据
     *
     * @param ipAddressVo 实例对象
     * @return 实例对象
     */
    IpAddress modify(IpAddressVo ipAddressVo) throws IpAddressExistException;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Integer id) throws IpAddressExistException;
}