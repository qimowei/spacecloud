package cn.nuaa.dao;

import cn.nuaa.entity.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/2/10 14:32
 * @Description: <使用mybatisPlus  继承BaseMapper<>不用再写简单方法>
 */
@Repository
public interface ProductTypeDao extends BaseMapper<ProductType>{

    /**
     * 查询所有分类的所有信息，供前台展示用的
     */
    public List<ProductType> selectAllFrontShow(String sortName);

    /**
     * 查询所有商品分类的信息,可加模糊查询
     * @return
     */
    public List<ProductType> selectAll(String name);

    /**
     * 根据名称查询信息
     * @param name
     * @return
     */
    ProductType selectByName(String name);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ProductType selectById(Integer id);
    /**
     * 根据sor_id查询信息
     * @param sortId
     * @return
     */
    ProductType selectBySortId(Integer sortId);
    /**
     * 根据id修改状态
     * @param id
     */
    void updateStatusById(@Param("id") Integer id,@Param("status") Integer status);
}
