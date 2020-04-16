package cn.nuaa.service;

import cn.nuaa.common.exception.ProductPriceExistException;
import cn.nuaa.entity.ProductPrice;
import cn.nuaa.vo.ProductPriceVo;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:57
 * @Description: <描述>
 */
public interface ProductPriceService {
    /**
     * 查询所有，用于表格
     * @return
     */
    List<ProductPrice> findAll();

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ProductPrice findById(Integer id);

    /**
     * 添加商品名称
     * @param productPriceVo
     * @throws ProductPriceExistException
     */
    void add(ProductPriceVo productPriceVo) throws ProductPriceExistException;

    /**
     * 根据id删除商品信息
     * @param id
     * @throws ProductPriceExistException
     */
    void removeById(Integer id) throws ProductPriceExistException;

    /**
     * 修改信息
     * @param productPriceVo
     * @throws ProductPriceExistException
     */
    void modify(ProductPriceVo productPriceVo) throws ProductPriceExistException;
}
