package cn.nuaa.dao;

import cn.nuaa.entity.ProductParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/2 17:06
 * @Description: <使用mybatisPlus  继承BaseMapper<>不用再写简单方法>
 */
@Repository
public interface ProductParamDao extends BaseMapper<ProductParam> {

    /**
     * 查询所有商品参数的信息,可加模糊查询
     * @return
     */
    List<ProductParam> selectAll();

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ProductParam selectById(Integer id);

    /**
     * 增加参数
     * @param productParam
     * @return
     */
    @Override
    int insert(ProductParam productParam);

    /**
     * 根据id更新参数
     * @param productParam
     * @return
     */
    @Override
    int updateById(ProductParam productParam);

    /**
     * 根据productTypeId查信息
     * @param productTypeId
     * @return
     */
    ProductParam selectByProductTypeId(Integer productTypeId);
}
