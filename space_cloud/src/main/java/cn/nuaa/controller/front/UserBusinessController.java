package cn.nuaa.controller.front;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.constant.PaginationConstant;
import cn.nuaa.entity.UserBusiness;
import cn.nuaa.service.UserBusinessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (UserBusiness)表控制层
 *
 * @author wpc
 * @since 2020-03-28 22:34:41
 */
@RestController
@RequestMapping("/api/userBusiness")
public class UserBusinessController {
    /**
     * 服务对象
     */
    @Autowired
    private UserBusinessService userBusinessService;

    /**
     * 查询所有名称信息并分类
     * @param userBusiness
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll(UserBusiness userBusiness, Integer page, @RequestParam(name = "limit", required = false) Integer pageSize) {
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
        List<UserBusiness> userBusinesses = userBusinessService.findAll(userBusiness);
        //将查询结果放到PageInfo里，方便调用
        PageInfo<UserBusiness> pageInfo = new PageInfo<>(userBusinesses);
        return ResponseResult.success(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public ResponseResult selectOne(Long id) {
        UserBusiness userBusiness = this.userBusinessService.findById(id);
        return ResponseResult.success(userBusiness);
    }

}