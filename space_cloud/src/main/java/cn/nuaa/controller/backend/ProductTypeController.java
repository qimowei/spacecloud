package cn.nuaa.controller.backend;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.constant.PaginationConstant;
import cn.nuaa.common.exception.ProductTypeExistException;
import cn.nuaa.entity.ProductType;
import cn.nuaa.service.ProductTypeService;
import cn.nuaa.vo.ProductTypeVo;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/2/10 16:02
 * @Description: <描述>
 */
@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 查询所有名称信息并分类
     * @param name
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll(String name, Integer page, @RequestParam(name = "limit", required = false) Integer pageSize) {
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
        List<ProductType> productTypes = productTypeService.findAll(name);
        //将查询结果放到PageInfo里，方便调用
        PageInfo<ProductType> pageInfo = new PageInfo<>(productTypes);
        return ResponseResult.success(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据id查找名称信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {
        ProductType productType = productTypeService.findById(id);
        return ResponseResult.success(productType);
    }

    /**
     * 添加商品名称
     *
     * @param productTypeVo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(ProductTypeVo productTypeVo) {
        try {
            productTypeService.add(productTypeVo);
            return ResponseResult.success();
        } catch (ProductTypeExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 删除商品名称
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResponseResult remove(Integer id) {
        try {
            productTypeService.removeById(id);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 批量删除商品名称
     *
     * @param productTypeVo
     * @return
     */
    @RequestMapping(value = "/batchRem", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseResult batchRem(ProductTypeVo productTypeVo) {
        String ids = productTypeVo.getIds();
        String[] a = ids.split(",");
        ResponseResult result = new ResponseResult();
        try {
            for (String id : a) {
                productTypeService.removeById(Integer.parseInt(id));
                System.out.println(Integer.parseInt(id));
                result = ResponseResult.success();
            }
        } catch (Exception e) {
            result = ResponseResult.fail(e.getMessage());
        }
        return result;
    }

    /**
     * 根据id修改状态
     *
     * @param id
     * @return
     */
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(Integer id) {
        productTypeService.modifyStatus(id);
        return ResponseResult.success();
    }

    /**
     * 批量修改状态
     *
     * @param productTypeVo
     * @return
     */
    @RequestMapping("/batchStatus")
    @ResponseBody
    public ResponseResult batchStatus(ProductTypeVo productTypeVo) {
        String ids = productTypeVo.getIds();
        String[] a = ids.split(",");
        ResponseResult result = new ResponseResult();
        try {
            for (String id : a) {
                productTypeService.modifyStatus(Integer.parseInt(id));
                result = ResponseResult.success();
            }
        } catch (Exception e) {
            result = ResponseResult.fail(e.getMessage());
        }
        return result;
    }

    /**
     * 修改商品名称信息
     *
     * @param productTypeVo
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(ProductTypeVo productTypeVo) {
        try {
            productTypeService.modify(productTypeVo);
            return ResponseResult.success();
        } catch (ProductTypeExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
