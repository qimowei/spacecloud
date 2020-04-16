package cn.nuaa.controller.backend;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.constant.PaginationConstant;
import cn.nuaa.common.exception.ProductSystemExistException;
import cn.nuaa.entity.ProductSystem;
import cn.nuaa.service.ProductSystemService;
import cn.nuaa.vo.ProductSystemVo;
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
 * @Date: 2020/3/3 17:47
 * @Description: <描述>
 */
@Controller
@RequestMapping("/backend/productSystem")
public class ProductSystemController {

    @Autowired
    private ProductSystemService productSystemService;

    /**
     * 查询所有系统信息并分类
     *
     * @param productSystemVo
     * @param page
     * @param pageSize (required=false作用是关闭不传值报错)
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll(ProductSystemVo productSystemVo, Integer page, @RequestParam(name = "limit", required = false) Integer pageSize) {
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
        //查询所有商品系统
        List<ProductSystem> productSystems = productSystemService.findAll(productSystemVo);
        //将查询结果放到PageInfo里，方便调用
        PageInfo<ProductSystem> pageInfo = new PageInfo<>(productSystems);
        return ResponseResult.success(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 查询所有系统信息,用于select展示
     * @return
     */
    @RequestMapping("/findAllShow")
    @ResponseBody
    public ResponseResult findAll(ProductSystemVo productSystemVo) {
        List<ProductSystem> productSystems = productSystemService.findAll(productSystemVo);
        return ResponseResult.success(productSystems);
    }

    /**
     * 根据id查找系统信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {
        ProductSystem productSystem = productSystemService.findById(id);
        return ResponseResult.success(productSystem);
    }

    /**
     * 添加商品系统
     *
     * @param productSystemVo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(ProductSystemVo productSystemVo) {
        try {
            productSystemService.add(productSystemVo);
            return ResponseResult.success();
        } catch (ProductSystemExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 删除商品系统
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResponseResult remove(Integer id) {
        try {
            productSystemService.removeById(id);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 批量删除商品系统
     *
     * @param productSystemVo
     * @return
     */
    @RequestMapping(value = "/batchRem", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseResult batchRem(ProductSystemVo productSystemVo) {
        String ids = productSystemVo.getIds();
        ResponseResult result = new ResponseResult();
        String[] a = ids.split(",");
        try {
            for (String id : a) {
                productSystemService.removeById(Integer.parseInt(id));
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
        try {
            productSystemService.modifyStatus(id);
            return ResponseResult.success();
        } catch (ProductSystemExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 批量修改状态
     *
     * @param productSystemVo
     * @return
     */
    @RequestMapping("/batchStatus")
    @ResponseBody
    public ResponseResult batchStatus(ProductSystemVo productSystemVo) {
        String ids = productSystemVo.getIds();
        ResponseResult result = new ResponseResult();
        String[] a = ids.split(",");
        try {
            for (String id : a) {
                productSystemService.modifyStatus(Integer.parseInt(id));
                System.out.println();
                result = ResponseResult.success();
            }
        } catch (Exception e) {
            result = ResponseResult.fail(e.getMessage());
        }
        return result;
    }

    /**
     * 修改商品系统信息
     *
     * @param productSystemVo
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(ProductSystemVo productSystemVo) {
        try {
            productSystemService.modify(productSystemVo);
            return ResponseResult.success();
        } catch (ProductSystemExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
