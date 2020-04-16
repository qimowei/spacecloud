package cn.nuaa.dao;

import cn.nuaa.entity.IpAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (IpAddress)表数据库访问层
 *
 * @author wpc
 * @since 2020-04-02 17:31:58
 */
@Repository
public interface IpAddressDao extends BaseMapper<IpAddress>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    IpAddress queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param ipAddress 实例对象
     * @return 对象列表
     */
    List<IpAddress> queryAll(IpAddress ipAddress);

    /**
     * 通过ip地址查找是否存在
     * @param ip
     * @return
     */
    IpAddress queryByIp(String ip);

}