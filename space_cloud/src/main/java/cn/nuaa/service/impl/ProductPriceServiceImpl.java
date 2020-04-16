package cn.nuaa.service.impl;

import cn.nuaa.common.exception.ProductParamExistException;
import cn.nuaa.common.exception.ProductPriceExistException;
import cn.nuaa.dao.ProductDao;
import cn.nuaa.dao.ProductPriceDao;
import cn.nuaa.entity.Product;
import cn.nuaa.entity.ProductPrice;
import cn.nuaa.service.ProductPriceService;
import cn.nuaa.vo.ProductPriceVo;
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
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceDao productPriceDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductPrice> findAll() {
        return productPriceDao.selectAll();
    }

    @Override
    public ProductPrice findById(Integer id) {
        return productPriceDao.selectById(id);
    }

    @Override
    public void add(ProductPriceVo productPriceVo) throws ProductPriceExistException {
        if (null == productPriceVo.getPrice() || "".equals(productPriceVo.getPrice())) {
            throw new ProductPriceExistException("价格不能为空");
        }
        if (null == productPriceVo.getProduct_type_id() || "".equals(productPriceVo.getProduct_type_id())) {
            throw new ProductPriceExistException("商品名称不能为空");
        }
        ProductPrice productPrice = new ProductPrice();
        productPrice.setPrice(productPriceVo.getPrice());
        productPrice.setProduct_type_id(productPriceVo.getProduct_type_id());
        productPriceDao.insert(productPrice);
    }

    @Override
    public void removeById(Integer id) throws ProductPriceExistException {
        List<Product> products = productDao.queryAll(null, null, null);
        if (products!=null){
            for (Product product:products){
                if (product.getUserOrder().getProductTypeId().equals(id)){
                    throw new ProductPriceExistException("商品有用户在使用，无法删除");
                }
            }
        }
        productPriceDao.deleteById(id);
    }

    @Override
    public void modify(ProductPriceVo productPriceVo) throws ProductPriceExistException {
        if (null == productPriceVo.getPrice() || "".equals(productPriceVo.getPrice())) {
            throw new ProductPriceExistException("价格不能为空");
        }
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId(productPriceVo.getId());
        productPrice.setPrice(productPriceVo.getPrice());
        productPrice.setProduct_type_id(productPriceVo.getProduct_type_id());
        productPriceDao.updateById(productPrice);
    }
}
