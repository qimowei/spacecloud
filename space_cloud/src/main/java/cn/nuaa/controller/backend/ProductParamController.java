package cn.nuaa.controller.backend;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.constant.PaginationConstant;
import cn.nuaa.common.exception.ProductParamExistException;
import cn.nuaa.entity.ProductParam;
import cn.nuaa.entity.ProductParam;
import cn.nuaa.service.ProductParamService;
import cn.nuaa.service.ProductParamService;
import cn.nuaa.vo.ProductParamVo;
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
 * @Date: 2020/3/2 17:06
 * @Description: <描述>
 */
@Controller
@RequestMapping("/backend/productParam")
public class ProductParamController {

    @Autowired
    private ProductParamService productParamService;

    /**
     * 查询所有参数并分类
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll(Integer page, @RequestParam(name = "limit", required = false) Integer pageSize) {
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
        List<ProductParam> productParams = productParamService.findAll();
        //将查询结果放到PageInfo里，方便调用
        PageInfo<ProductParam> pageInfo = new PageInfo<>(productParams);
        return ResponseResult.success(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据id查找机房信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {
        ProductParam productParam = productParamService.findById(id);
        return ResponseResult.success(productParam);
    }

    /**
     * 添加商品机房
     *
     * @param productParamVo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(ProductParamVo productParamVo) {
        try {
            productParamService.add(productParamVo);
            return ResponseResult.success();
        } catch (ProductParamExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 删除商品机房
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResponseResult remove(Integer id) {
        try {
            productParamService.removeById(id);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 批量删除商品机房
     *
     * @param productParamVo
     * @return
     */
    @RequestMapping(value = "/batchRem", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseResult batchRem(ProductParamVo productParamVo) {
        ResponseResult result = new ResponseResult();
        //传入json形式的多个id集合
        String ids = productParamVo.getIds();
        //分割出里面的id并放入数组
        String[] a = ids.split(",");
        try {
            for (String id : a) {
                productParamService.removeById(Integer.parseInt(id));
                result = ResponseResult.success();
            }
        } catch (Exception e) {
            result = ResponseResult.fail(e.getMessage());
        }
        return result;
    }

    /**
     * 修改商品机房信息
     *
     * @param productParamVo
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(ProductParamVo productParamVo) {
        try {
            productParamService.modify(productParamVo);
            return ResponseResult.success();
        } catch (ProductParamExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
