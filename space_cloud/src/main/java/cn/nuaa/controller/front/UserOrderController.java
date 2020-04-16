package cn.nuaa.controller.front;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.constant.PaginationConstant;
import cn.nuaa.common.exception.UserOrderExistException;
import cn.nuaa.entity.UserOrder;
import cn.nuaa.service.UserOrderService;
import cn.nuaa.vo.UserOrderVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (UserOrder)表控制层
 *
 * @author wpc
 * @since 2020-03-28 19:43:12
 */
@RestController
@RequestMapping("/api/userOrder")
public class UserOrderController {
    /**
     * 服务对象
     */
    @Autowired
    private UserOrderService userOrderService;

    /**
     * 查询所有名称信息并分类
     *
     * @param userOrderVo
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll(UserOrderVo userOrderVo, Integer page, @RequestParam(name = "limit", required = false) Integer pageSize) {
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
        List<UserOrder> userOrders = userOrderService.findAll(userOrderVo);
        //将查询结果放到PageInfo里，方便调用
        PageInfo<UserOrder> pageInfo = new PageInfo<>(userOrders);
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
        UserOrderVo userOrderVo = this.userOrderService.queryById(id);
        return ResponseResult.success(userOrderVo);
    }

    @GetMapping("/removeById")
    public ResponseResult removeById(Long id) {
        ResponseResult result = null;
        try {
            this.userOrderService.deleteById(id);
            result = ResponseResult.success("删除成功");
        } catch (UserOrderExistException e) {
            result = ResponseResult.fail("删除失败");
        }
        return result;
    }

    /**
     * 查询订单状态
     * @param userAccount
     * @return
     */
    @GetMapping("/findOrderStatus")
    public ResponseResult findOrderStatus(String userAccount){
        List<UserOrder> userOrders=userOrderService.findOrderStatusByUserAccount(userAccount);
        return ResponseResult.success(userOrders);
    }
}