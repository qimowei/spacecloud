package cn.nuaa.service.impl;

import cn.nuaa.common.constant.ProductTypeConstant;
import cn.nuaa.common.exception.ProductTypeExistException;
import cn.nuaa.dao.ProductParamDao;
import cn.nuaa.dao.ProductPriceDao;
import cn.nuaa.dao.ProductRoomDao;
import cn.nuaa.dao.ProductTypeDao;
import cn.nuaa.entity.ProductParam;
import cn.nuaa.entity.ProductPrice;
import cn.nuaa.entity.ProductRoom;
import cn.nuaa.entity.ProductType;
import cn.nuaa.service.ProductTypeService;
import cn.nuaa.vo.ProductTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/2/12 17:11
 * @Description: <描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private ProductParamDao productParamDao;
    @Autowired
    private ProductPriceDao productPriceDao;
    @Autowired
    private ProductRoomDao productRoomDao;

    @Override
    public List<ProductType> findAll(String name) {
        return productTypeDao.selectAll(name);
    }

    @Override
    public ProductType findById(Integer id) {
        return productTypeDao.selectById(id);
    }

    @Override
    public void add(ProductTypeVo productTypeVo) throws ProductTypeExistException {
        ProductType productTypeByName = productTypeDao.selectByName(productTypeVo.getName());
        if (null != productTypeByName) {
            throw new ProductTypeExistException("商品类型已存在");
        }
        if (productTypeVo.getName() == null || productTypeVo.getName() == "") {
            throw new ProductTypeExistException("商品名不能为空");
        }
        ProductType productType = new ProductType();
        productType.setName(productTypeVo.getName());
        productType.setStatus(ProductTypeConstant.PRODUCTTYPE_STATUA_ENABLE);
        productType.setSort_id(productTypeVo.getSort_id());
        productTypeDao.insert(productType);
    }

    @Override
    public void removeById(Integer id) throws ProductTypeExistException {
        ProductParam productParam = productParamDao.selectByProductTypeId(id);
        ProductPrice productPrice = productPriceDao.selectByProductTypeId(id);
        ProductRoom productRoom = productRoomDao.selectByProductTypeId(id);
        if (productParam!=null||productPrice!=null||productRoom!=null){
            throw new ProductTypeExistException("分类下还有参数，不可删除");
        }
        productTypeDao.deleteById(id);
    }

    @Override
    public void modifyStatus(Integer id) {
        ProductType productType = productTypeDao.selectById(id);
        Integer status = productType.getStatus();
        if (status == ProductTypeConstant.PRODUCTTYPE_STATUA_ENABLE) {
            status = ProductTypeConstant.PRODUCTTYPE_STATUS_DISABLE;
        } else {
            status = ProductTypeConstant.PRODUCTTYPE_STATUA_ENABLE;
        }
        productTypeDao.updateStatusById(id, status);
    }

    @Override
    public void modify(ProductTypeVo productTypeVo) throws ProductTypeExistException {
        ProductType productTypeByName = productTypeDao.selectByName(productTypeVo.getName());
        //对比，如果是本身，也放行更改，只有不是本身才去对比是否存在相同名称
        if (null != productTypeByName && !productTypeByName.getId().equals(productTypeVo.getId())) {
            throw new ProductTypeExistException("商品类型已存在");
        }
        if (productTypeVo.getName() == null || productTypeVo.getName() == "") {
            throw new ProductTypeExistException("商品名不能为空");
        }
        ProductType productType = new ProductType();
        productType.setId(productTypeVo.getId());
        productType.setName(productTypeVo.getName());
        productType.setSort_id(productTypeVo.getSort_id());
        productTypeDao.updateById(productType);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductType> findAllFrontShow(String sortName) {
        List<ProductType> productTypes = productTypeDao.selectAllFrontShow(sortName);
        return productTypes;
    }
}
