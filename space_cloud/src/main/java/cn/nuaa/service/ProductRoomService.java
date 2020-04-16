package cn.nuaa.service;

import cn.nuaa.common.exception.ProductRoomExistException;
import cn.nuaa.entity.ProductRoom;
import cn.nuaa.vo.ProductRoomVo;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:57
 * @Description: <描述>
 */
public interface ProductRoomService {
    /**
     * 查询所有，用于表格
     * @param country
     * @return
     */
    public List<ProductRoom> findAll(String country);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ProductRoom findById(Integer id);

    /**
     * 添加商品名称
     * @param productRoomVo
     * @throws ProductRoomExistException
     */
    void add(ProductRoomVo productRoomVo) throws ProductRoomExistException;

    /**
     * 根据id删除商品信息
     * @param id
     * @throws ProductRoomExistException
     */
    void removeById(Integer id) throws ProductRoomExistException;

    /**
     * 修改信息
     * @param productRoomVo
     * @throws ProductRoomExistException
     */
    void modify(ProductRoomVo productRoomVo) throws ProductRoomExistException;
}
