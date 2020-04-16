package cn.nuaa.service.impl;

import cn.nuaa.common.exception.ProductExistException;
import cn.nuaa.dao.*;
import cn.nuaa.entity.*;
import cn.nuaa.service.ProductService;
import cn.nuaa.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Product)表服务实现类
 *
 * @author wpc
 * @since 2020-04-02 17:36:04
 */
@Service("productService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private ProductRoomDao productRoomDao;
    @Autowired
    private ProductParamDao productParamDao;
    @Autowired
    private ProductSystemDao productSystemDao;

    @Override
    public List<Product> findAll(ProductVo productVo) throws ProductExistException {
        String userAccount = productVo.getUserAccount();
        String sortName = productVo.getSortName();
        String searchDate = productVo.getSearchDate();
        List<Product> products = productDao.queryAll(userAccount, sortName,searchDate);
        if (products!=null){
            for (Product product:products){
                if (product.getUserOrder().getProductTypeId()==null||product.getUserOrder().getProductSystemId()==null){
                    throw new ProductExistException("参数id不能为空,请重试或联系管理员");
                }
                //通过productTypeId查productType和productRoom和productTypeParam
                ProductType productType = productTypeDao.selectById(product.getUserOrder().getProductTypeId());
                ProductRoom productRoom = productRoomDao.selectByProductTypeId(product.getUserOrder().getProductTypeId());
                ProductParam productParam = productParamDao.selectByProductTypeId(product.getUserOrder().getProductTypeId());
                //根据productSystemId查系统
                ProductSystem productSystem = productSystemDao.selectById(product.getUserOrder().getProductSystemId());

                product.setProductType(productType);
                product.setProductRoom(productRoom);
                product.setProductParam(productParam);
                product.setProductSystem(productSystem);
            }
        }
        return products;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product findById(Long id) {
        return this.productDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product add(Product product) {
        this.productDao.insert(product);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product modify(Product product) {
        this.productDao.updateById(product);
        return this.findById(product.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Long id) {
        return this.productDao.deleteById(id) > 0;
    }

    @Override
    public List<Product> findProductByUserAccount(String userAccount) {
        List<Product> products = this.productDao.queryAll(userAccount, null, null);
        return products;
    }
}