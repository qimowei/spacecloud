package cn.nuaa.dao;

import cn.nuaa.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (Product)表数据库访问层
 *
 * @author wpc
 * @since 2020-04-02 17:36:03
 */
@Repository
public interface ProductDao extends BaseMapper<Product>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(Long id);

    /**
     * 查询全部，带用户名查
     * @param userAccount
     * @param sortName
     * @return
     */
    List<Product> queryAll(@Param("userAccount")String userAccount,@Param("sortName")String sortName,@Param("searchDate")String searchDate);

    /**
     * 根据sort分类id和用户名查询返回条数
     * @param product
     * @return
     */
    int queryCountBySortId(Product product);


}