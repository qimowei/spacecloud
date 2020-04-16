package cn.nuaa.controller.front;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.entity.ProductType;
import cn.nuaa.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/4/4 22:42
 * @Description: <描述>
 */
@RestController
@RequestMapping("/api/frontshow")
public class FrontShowController {

    /**
     * 服务对象
     */
    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 查询所有名称信息并分类,供前台展示用
     * @return
     */
    @GetMapping("/findAllFrontShow")
    public ResponseResult findAllFrontShow(String sortName) {
        //查询所有名称信息并分类,供前台展示用
        List<ProductType> productTypes = productTypeService.findAllFrontShow(sortName);
        return ResponseResult.success(productTypes);
    }
}
