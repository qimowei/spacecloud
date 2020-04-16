package cn.nuaa.service.impl;

import cn.nuaa.common.exception.ProductParamExistException;
import cn.nuaa.common.exception.ProductPriceExistException;
import cn.nuaa.common.exception.ProductSystemExistException;
import cn.nuaa.dao.ProductDao;
import cn.nuaa.dao.ProductParamDao;
import cn.nuaa.entity.Product;
import cn.nuaa.entity.ProductParam;
import cn.nuaa.service.ProductParamService;
import cn.nuaa.vo.ProductParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/2 17:11
 * @Description: <描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductParamServiceImpl implements ProductParamService {
    @Autowired
    private ProductParamDao productParamDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductParam> findAll() {
        return productParamDao.selectAll();
    }

    @Override
    public ProductParam findById(Integer id) {
        return productParamDao.selectById(id);
    }

    @Override
    public void add(ProductParamVo productParamVo) throws ProductParamExistException {
        if (null == productParamVo.getProduct_type_id() || "".equals(productParamVo.getProduct_type_id())) {
            throw new ProductParamExistException("商品名称不能为空");
        }
        ProductParam productParam = new ProductParam();
        productParam.setCputype(productParamVo.getCputype());
        productParam.setCpu(productParamVo.getCpu());
        productParam.setRam(productParamVo.getRam());
        productParam.setBandwidth(productParamVo.getBandwidth());
        productParam.setSysdisk(productParamVo.getSysdisk());
        productParam.setDatadisk(productParamVo.getDatadisk());
        productParam.setTraffic(productParamVo.getTraffic());
        productParam.setSql_size(productParamVo.getSql_size());
        productParam.setProduct_type_id(productParamVo.getProduct_type_id());
        productParamDao.insert(productParam);
    }

    @Override
    public void removeById(Integer id) throws ProductParamExistException {
        List<Product> products = productDao.queryAll(null, null, null);
        if (products!=null){
            for (Product product:products){
                if (product.getUserOrder().getProductTypeId().equals(id)){
                    throw new ProductParamExistException("商品有用户在使用，无法删除");
                }
            }
        }
        productParamDao.deleteById(id);
    }

    @Override
    public void modify(ProductParamVo productParamVo) throws ProductParamExistException {
        if (null == productParamVo.getId() || "".equals(productParamVo.getId())) {
            throw new ProductParamExistException("id为空，请联系管理员");
        }
        ProductParam productParam = new ProductParam();
        productParam.setId(productParamVo.getId());
        productParam.setCputype(productParamVo.getCputype());
        productParam.setCpu(productParamVo.getCpu());
        productParam.setRam(productParamVo.getRam());
        productParam.setBandwidth(productParamVo.getBandwidth());
        productParam.setSysdisk(productParamVo.getSysdisk());
        productParam.setDatadisk(productParamVo.getDatadisk());
        productParam.setTraffic(productParamVo.getTraffic());
        productParam.setSql_size(productParamVo.getSql_size());
        productParamDao.updateById(productParam);
    }
}
