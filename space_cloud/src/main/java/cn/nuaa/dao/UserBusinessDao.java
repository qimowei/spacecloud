package cn.nuaa.dao;

import cn.nuaa.entity.UserBusiness;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (UserBusiness)表数据库访问层
 *
 * @author wpc
 * @since 2020-03-28 22:34:41
 */
@Repository
public interface UserBusinessDao extends BaseMapper<UserBusiness>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserBusiness queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userBusiness 根据消费类型查询
     * @return 对象列表
     */
    List<UserBusiness> queryAll(UserBusiness userBusiness);

}