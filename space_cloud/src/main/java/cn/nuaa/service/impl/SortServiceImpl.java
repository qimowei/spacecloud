package cn.nuaa.service.impl;

import cn.nuaa.common.constant.ProductTypeConstant;
import cn.nuaa.common.exception.ProductTypeExistException;
import cn.nuaa.common.exception.SortExistException;
import cn.nuaa.dao.SortDao;
import cn.nuaa.entity.ProductType;
import cn.nuaa.entity.Sort;
import cn.nuaa.service.SortService;
import cn.nuaa.vo.SortVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/2/10 17:53
 * @Description: <描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class SortServiceImpl implements SortService{

    @Autowired
    private SortDao sortDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Sort> findAll() {
        List<Sort> sorts = sortDao.selectList(null);
        return sorts;
    }

    @Override
    public Sort findById(Integer id) {
        Sort sort = sortDao.selectById(id);
        return sort;
    }

    @Override
    public void add(SortVo sortVo) throws SortExistException {
        Sort sortByName=sortDao.selectByName(sortVo.getName());
        if (null != sortByName) {
            throw new SortExistException("商品分类已存在");
        }
        if (sortVo.getName() == null || sortVo.getName() == "") {
            throw new SortExistException("商品名不能为空");
        }
        Sort sort = new Sort();
        sort.setName(sortVo.getName());
        sortDao.insert(sort);
    }

    @Override
    public void removeById(Integer id) throws SortExistException {
        try {
            sortDao.deleteById(id);
        } catch (Exception e) {
            throw new SortExistException("删除失败");
        }
    }

    @Override
    public void modify(Sort sort) throws SortExistException {
        Sort selectByName = sortDao.selectByName(sort.getName());
        //对比，如果是本身，也放行更改，只有不是本身才去对比是否存在相同名称
        if (null != selectByName && !selectByName.getId().equals(sort.getId())) {
            throw new SortExistException("商品类型已存在");
        }
        if (sort.getName() == null || sort.getName() == "") {
            throw new SortExistException("商品类型不能为空");
        }
        Sort sort1 = new Sort();
        sort1.setId(sort.getId());
        sort1.setName(sort.getName());
        sortDao.updateById(sort1);
    }
}
