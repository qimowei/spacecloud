package cn.nuaa.service;


import cn.nuaa.common.exception.ProductTypeExistException;
import cn.nuaa.entity.ProductType;
import cn.nuaa.vo.ProductTypeVo;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/2/12 17:07
 * @Description: <描述>
 */
public interface ProductTypeService {

    /**
     * 查询所有，用于表格
     * @return
     */
    public List<ProductType> findAll(String name);

    /**
     * 根据id查询名称信息
     * @param id
     * @return
     */
    ProductType findById(Integer id);

    /**
     * 添加商品名称
     * @param productTypeVo
     */
    void add(ProductTypeVo productTypeVo) throws ProductTypeExistException;

    /**
     * 根据id删除商品信息
     * @param id
     */
    void removeById(Integer id) throws ProductTypeExistException;

    /**
     * 根据id修改状态
     * @param id
     */
    void modifyStatus(Integer id);

    /**
     *
     * @param productTypeVo
     */
    void modify(ProductTypeVo productTypeVo) throws ProductTypeExistException;

    /**
     * 查询所有名称信息并分类,供前台展示用
     * @return
     */
    List<ProductType> findAllFrontShow(String sortName);

}
