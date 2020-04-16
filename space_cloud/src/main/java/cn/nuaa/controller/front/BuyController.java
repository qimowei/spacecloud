package cn.nuaa.controller.front;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.exception.UserOrderExistException;
import cn.nuaa.entity.UserOrder;
import cn.nuaa.service.UserOrderService;
import cn.nuaa.service.UserService;
import cn.nuaa.vo.UserOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wpc
 * @Date: 2020/4/6 19:30
 * @Description: <提交订单并付款的控制层>
 */
@RestController
@RequestMapping("/api/buy")
public class BuyController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserOrderService userOrderService;

    /**
     * 查余额，看够不够买相应价格东西的,(这个控制层接口不一定用得到)，应该多数用service层了
     * @param userAccount
     * @param productPrice
     * @return
     */
    @GetMapping("/checkBalance")
    public ResponseResult checkBalance(String userAccount,Double productPrice){
        int code=userService.checkBalance(userAccount,productPrice);
        ResponseResult result=null;
        //返回的状态，如果code==0，说明余额足够
        if (code==0){
            result=ResponseResult.success("钱够了");
        }else if (code==1){
            result=ResponseResult.fail("余额不足，请充值");
        }else {
            result=ResponseResult.fail("用户不存在");
        }
        return result;
    }

    /**
     * 下单接口,用vo的原因时需要提交好多个订单参数
     * @param userOrderVo
     * @return
     */
    @GetMapping("/placeOrder")
    public ResponseResult placeOrder(UserOrderVo userOrderVo){
        UserOrder userOrder = null;
        try {
            userOrder = userOrderService.add(userOrderVo);
            return ResponseResult.success(userOrder);
        } catch (UserOrderExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @GetMapping("/pay")
    public ResponseResult pay(String userAccount,Long orderId){
        try {
            userOrderService.pay(userAccount,orderId);
            return ResponseResult.success("支付成功");
        } catch (UserOrderExistException e) {
            return ResponseResult.fail(e.getMessage()+"，支付失败!");
        }
    }
}
