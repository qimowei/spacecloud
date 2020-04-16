package cn.nuaa.dao;

import cn.nuaa.entity.ProductRoom;
import cn.nuaa.entity.ProductRoom;
import cn.nuaa.entity.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/2 17:06
 * @Description: <使用mybatisPlus  继承BaseMapper<>不用再写简单方法>
 */
@Repository
public interface ProductRoomDao extends BaseMapper<ProductRoom> {

    /**
     * 查询所有商品主机的信息,可加模糊查询
     * @param country
     * @return
     */
    List<ProductRoom> selectAll(String country);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ProductRoom selectById(Integer id);

    /**
     * 根据productType的id来查机房
     * @param productTypeId
     */
    ProductRoom selectByProductTypeId(Integer productTypeId);
}
