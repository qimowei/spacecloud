package cn.nuaa.service.impl;

import cn.nuaa.common.constant.ProductSystemConstant;
import cn.nuaa.common.exception.ProductExistException;
import cn.nuaa.common.exception.ProductSystemExistException;
import cn.nuaa.dao.ProductDao;
import cn.nuaa.dao.ProductSystemDao;
import cn.nuaa.entity.*;
import cn.nuaa.service.ProductSystemService;
import cn.nuaa.vo.ProductSystemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:59
 * @Description: <描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductSystemServiceImpl implements ProductSystemService {
    @Autowired
    private ProductSystemDao productSystemDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductSystem> findAll(ProductSystemVo ProductSystemVo) {
        ProductSystem productSystem = new ProductSystem();
        BeanUtils.copyProperties(ProductSystemVo, productSystem);
        return productSystemDao.selectAll(productSystem);
    }

    @Override
    public ProductSystem findById(Integer id) {
        return productSystemDao.selectById(id);
    }

    @Override
    public void add(ProductSystemVo productSystemVo) throws ProductSystemExistException {
        if (productSystemVo.getSysType() == null || productSystemVo.getSysType() == "") {
            throw new ProductSystemExistException("系统类型不能为空");
        }
        ProductSystem productSystem = new ProductSystem();
        productSystem.setSysType(productSystemVo.getSysType());
        productSystem.setSysVersion(productSystemVo.getSysVersion());
        productSystem.setStatus(ProductSystemConstant.PRODUCTSYSTEM_STATUA_ENABLE);
        productSystemDao.insert(productSystem);
    }

    @Override
    public void removeById(Integer id) throws ProductSystemExistException {
        List<Product> products = productDao.queryAll(null, null, null);
        if (products!=null){
            for (Product product:products){
                if (product.getUserOrder().getProductSystemId().equals(id)){
                    throw new ProductSystemExistException("有系统被占用，无法删除");
                }
            }
        }
        productSystemDao.deleteById(id);
    }

    @Override
    public void modifyStatus(Integer id) throws ProductSystemExistException {
        List<Product> products = productDao.queryAll(null, null, null);
        if (products!=null){
            for (Product product:products){
                if (product.getUserOrder().getProductSystemId().equals(id)){
                    throw new ProductSystemExistException("系统被占用，无法修改状态");
                }
            }
        }
        ProductSystem productSystem = productSystemDao.selectById(id);
        Integer status = productSystem.getStatus();
        if (status == ProductSystemConstant.PRODUCTSYSTEM_STATUA_ENABLE) {
            status = ProductSystemConstant.PRODUCTSYSTEM_STATUS_DISABLE;
        } else {
            status = ProductSystemConstant.PRODUCTSYSTEM_STATUA_ENABLE;
        }
        productSystemDao.updateStatusById(id, status);
    }

    @Override
    public void modify(ProductSystemVo productSystemVo) throws ProductSystemExistException {
        if (productSystemVo.getSysType() == null || productSystemVo.getSysType() == "") {
            throw new ProductSystemExistException("系统类型不能为空");
        }
        ProductSystem productSystem = new ProductSystem();
        productSystem.setId(productSystemVo.getId());
        productSystem.setSysType(productSystemVo.getSysType());
        productSystem.setSysVersion(productSystemVo.getSysVersion());
        productSystemDao.updateById(productSystem);
    }
}
