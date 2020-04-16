package cn.nuaa.service;


import cn.nuaa.common.exception.ProductParamExistException;
import cn.nuaa.entity.ProductParam;
import cn.nuaa.vo.ProductParamVo;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/2 17:06
 * @Description: <描述>
 */
public interface ProductParamService {

    /**
     * 查询所有，用于表格
     * @return
     */
    public List<ProductParam> findAll();

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ProductParam findById(Integer id);

    /**
     * 添加商品名称
     * @param productTypeVo
     */
    void add(ProductParamVo productTypeVo) throws ProductParamExistException;

    /**
     * 根据id删除商品信息
     * @param id
     */
    void removeById(Integer id) throws ProductParamExistException;

    /**
     *
     * @param productTypeVo
     */
    void modify(ProductParamVo productTypeVo) throws ProductParamExistException;

}
