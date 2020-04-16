package cn.nuaa.service;

import cn.nuaa.common.exception.ProductSystemExistException;
import cn.nuaa.entity.ProductSystem;
import cn.nuaa.vo.ProductSystemVo;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:57
 * @Description: <描述>
 */
public interface ProductSystemService {
    /**
     * 查询所有，用于表格
     * @param ProductSystemVo
     * @return
     */
    public List<ProductSystem> findAll(ProductSystemVo ProductSystemVo);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ProductSystem findById(Integer id);

    /**
     * 添加商品名称
     * @param productSystemVo
     * @throws ProductSystemExistException
     */
    void add(ProductSystemVo productSystemVo) throws ProductSystemExistException;

    /**
     * 根据id删除商品信息
     * @param id
     * @throws ProductSystemExistException
     */
    void removeById(Integer id) throws ProductSystemExistException;

    /**
     * 根据id修改状态
     * @param id
     */
    void modifyStatus(Integer id) throws ProductSystemExistException;

    /**
     * 修改信息
     * @param productSystemVo
     * @throws ProductSystemExistException
     */
    void modify(ProductSystemVo productSystemVo) throws ProductSystemExistException;
}
