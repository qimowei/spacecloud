package cn.nuaa.dao;

import cn.nuaa.entity.ProductPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/2 17:06
 * @Description: <使用mybatisPlus  继承BaseMapper<>不用再写简单方法>
 */
@Repository
public interface ProductPriceDao extends BaseMapper<ProductPrice> {

    /**
     * 查询所有商品主机的信息,可加模糊查询
     * @return
     */
    List<ProductPrice> selectAll();

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ProductPrice selectById(Integer id);

    /**
     * 根据productTypeId查询信息
     * @param productTypeId
     * @return
     */
    ProductPrice selectByProductTypeId(Integer productTypeId);
}
