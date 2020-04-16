package cn.nuaa.dao;

import cn.nuaa.entity.ProductSystem;
import cn.nuaa.entity.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:59
 * @Description: <使用mybatisPlus  继承BaseMapper<>不用再写简单方法>
 */
@Repository
public interface ProductSystemDao extends BaseMapper<ProductSystem>{

    /**
     * 查询所有商品系统的信息,可加模糊查询
     * @param ProductSystem
     * @return
     */
    List<ProductSystem> selectAll(ProductSystem ProductSystem);

    /**
     * 根据id修改状态
     * @param id
     * @param status
     */
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

}
