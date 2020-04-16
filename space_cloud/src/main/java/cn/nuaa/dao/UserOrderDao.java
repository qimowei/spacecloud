package cn.nuaa.dao;

import cn.nuaa.entity.ProductType;
import cn.nuaa.entity.UserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (UserOrder)表数据库访问层
 *
 * @author wpc
 * @since 2020-03-28 19:43:12
 */
@Repository
public interface UserOrderDao extends BaseMapper<UserOrder>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserOrder queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param name ,用于根据商品名称搜索订单,这里的name是商品名称表productType里面的name
     * @return 对象列表
     */
    List<UserOrder> queryAll(@Param("name")String name,@Param("userAccount")String userAccount);

}