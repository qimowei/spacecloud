package cn.nuaa.service;

import cn.nuaa.common.exception.ProductExistException;
import cn.nuaa.entity.Product;
import cn.nuaa.entity.UserOrder;
import cn.nuaa.vo.ProductVo;
import cn.nuaa.vo.UserOrderVo;

import java.util.List;

/**
 * (Product)表服务接口
 *
 * @author wpc
 * @since 2020-04-02 17:36:03
 */
public interface ProductService {

    /**
     * 查询所有，用于表格
     * @param productVo
     * @return
     */
    List<Product> findAll(ProductVo productVo) throws ProductExistException;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product findById(Long id);

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product add(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product modify(Product product);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean removeById(Long id);

    /**
     * 根据用户名查信息
     * @param userAccount
     * @return
     */
    List<Product> findProductByUserAccount(String userAccount);
}