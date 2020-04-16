package cn.nuaa.service;

import cn.nuaa.common.exception.SortExistException;
import cn.nuaa.entity.Sort;
import cn.nuaa.vo.SortVo;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/2/10 16:01
 * @Description: <描述>
 */

public interface SortService {

    /**
     * 查询所有分类信息
     * @return
     */
    public List<Sort> findAll();

    /**
     *根据id查询信息
     * @param id
     * @return
     */
    Sort findById(Integer id);

    /**
     * 添加分类信息
     * @param sortVo
     */
    void add(SortVo sortVo) throws SortExistException;

    /**
     * 根据id删除商品分类
     * @param id
     */
    void removeById(Integer id) throws SortExistException;

    /**
     * 根据id修改分类信息
     * @param sort
     */
    void modify(Sort sort) throws SortExistException;

}
