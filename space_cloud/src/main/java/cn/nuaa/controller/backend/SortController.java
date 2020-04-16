package cn.nuaa.controller.backend;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.exception.ProductTypeExistException;
import cn.nuaa.common.exception.SortExistException;
import cn.nuaa.entity.ProductType;
import cn.nuaa.entity.Sort;
import cn.nuaa.service.SortService;
import cn.nuaa.vo.ProductTypeVo;
import cn.nuaa.vo.SortVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/2/10 17:59
 * @Description: <描述>
 */
@Controller
@RequestMapping("/backend/sort")
public class SortController {

    @Autowired
    private SortService sortService;

    /**
     * 查询所有分类信息
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll(){
        List<Sort> sorts = sortService.findAll();
        return ResponseResult.success(sorts);
    }

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id){
        Sort sort = sortService.findById(id);
        return ResponseResult.success(sort);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(SortVo sortVo){
        try {
            sortService.add(sortVo);
            return ResponseResult.success();
        } catch (SortExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 删除商品分类
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public ResponseResult remove(Integer id) {
        try {
            sortService.removeById(id);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 修改商品分类信息
     *
     * @param sort
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(Sort sort) {
        try {
            sortService.modify(sort);
            return ResponseResult.success();
        } catch (SortExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

}
