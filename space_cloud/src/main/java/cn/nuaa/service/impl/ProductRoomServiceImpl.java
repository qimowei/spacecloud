package cn.nuaa.service.impl;

import cn.nuaa.common.exception.ProductPriceExistException;
import cn.nuaa.common.exception.ProductRoomExistException;
import cn.nuaa.dao.ProductDao;
import cn.nuaa.dao.ProductRoomDao;
import cn.nuaa.entity.Product;
import cn.nuaa.entity.ProductRoom;
import cn.nuaa.service.ProductRoomService;
import cn.nuaa.vo.ProductRoomVo;
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
public class ProductRoomServiceImpl implements ProductRoomService {
    @Autowired
    private ProductRoomDao productRoomDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductRoom> findAll(String country) {
        return productRoomDao.selectAll(country);
    }

    @Override
    public ProductRoom findById(Integer id) {
        return productRoomDao.selectById(id);
    }

    @Override
    public void add(ProductRoomVo productRoomVo) throws ProductRoomExistException {
        if (productRoomVo.getCountry() == null || productRoomVo.getCountry() == "") {
            throw new ProductRoomExistException("国家不能为空");
        }
        if (productRoomVo.getCity() == null || productRoomVo.getCity() == "") {
            throw new ProductRoomExistException("城市不能为空");
        }
        if (null == productRoomVo.getProduct_type_id() || "".equals(productRoomVo.getProduct_type_id())) {
            throw new ProductRoomExistException("商品名称不能为空");
        }
        ProductRoom productRoom = new ProductRoom();
        productRoom.setCountry(productRoomVo.getCountry());
        productRoom.setCity(productRoomVo.getCity());
        productRoom.setProduct_type_id(productRoomVo.getProduct_type_id());
        productRoomDao.insert(productRoom);
    }

    @Override
    public void removeById(Integer id) throws ProductRoomExistException {
        List<Product> products = productDao.queryAll(null, null, null);
        if (products!=null){
            for (Product product:products){
                if (product.getUserOrder().getProductTypeId().equals(id)){
                    throw new ProductRoomExistException("商品有用户在使用，无法删除");
                }
            }
        }
        productRoomDao.deleteById(id);
    }

    @Override
    public void modify(ProductRoomVo productRoomVo) throws ProductRoomExistException {
        if (productRoomVo.getCountry() == null || productRoomVo.getCountry() == "") {
            throw new ProductRoomExistException("国家不能为空");
        }
        if (productRoomVo.getCity() == null || productRoomVo.getCity() == "") {
            throw new ProductRoomExistException("城市不能为空");
        }
        ProductRoom productRoom = new ProductRoom();
        productRoom.setId(productRoomVo.getId());
        productRoom.setCountry(productRoomVo.getCountry());
        productRoom.setCity(productRoomVo.getCity());
        productRoomDao.updateById(productRoom);
    }
}
