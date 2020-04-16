package cn.nuaa.controller.front;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.constant.PaginationConstant;
import cn.nuaa.common.exception.ProductExistException;
import cn.nuaa.entity.Product;
import cn.nuaa.entity.UserOrder;
import cn.nuaa.service.ProductService;
import cn.nuaa.vo.ProductVo;
import cn.nuaa.vo.UserOrderVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Product)表控制层
 *
 * @author wpc
 * @since 2020-04-02 17:36:04
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
    /**
     * 服务对象
     */
    @Autowired
    private ProductService productService;

    /**
     * 查询所有名称信息并分类
     * @param productVo
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll(ProductVo productVo, Integer page, @RequestParam(name = "limit", required = false) Integer pageSize) {
        /**
         * pagehelper插件分页,page和limit(pageSize)从前端获取
         */
        if (ObjectUtils.isEmpty(page)) {
            //判断pageNum是否为空，如果是，则给PAGE_NUM的值1
            page = PaginationConstant.PAGE_NUM;
        }
        if (ObjectUtils.isEmpty(pageSize)) {
            //判断pageSize是否为空，如果是，则给PAGE_SIZE的值1
            pageSize = PaginationConstant.PAGE_SIZE;
        }
        //初始化pageHelper
        PageHelper.startPage(page, pageSize);
        //查询所有商品名称
        List<Product> products = null;
        try {
            products = productService.findAll(productVo);
            //将查询结果放到PageInfo里，方便调用
            PageInfo<Product> pageInfo = new PageInfo<>(products);
            return ResponseResult.success(pageInfo.getTotal(), pageInfo.getList());
        } catch (ProductExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public ResponseResult selectOne(Long id) {
        Product product = this.productService.findById(id);
        return ResponseResult.success(product);
    }

    @GetMapping("/findProductByUserAccount")
    public ResponseResult findProductByUserAccount(String userAccount){
        List<Product> products=this.productService.findProductByUserAccount(userAccount);
        return ResponseResult.success(products);
    }

}