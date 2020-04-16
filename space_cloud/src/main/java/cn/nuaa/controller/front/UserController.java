package cn.nuaa.controller.front;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.exception.UserExistException;
import cn.nuaa.service.UserService;
import cn.nuaa.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Author: wpc
 * @Date: 2020/1/31 15:08
 * @description: <描述>
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据adminAccount查找管理员信息
     *
     * @param userAccount
     * @return
     */
    @RequestMapping("/findByUserAccount")
    @ResponseBody
    public ResponseResult findByUserAccount(String userAccount) {
        try {
            UserVo userVo = userService.findByUserAccount(userAccount);
            return ResponseResult.success(userVo);
        } catch (UserExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 修改用户信息
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(UserVo userVo) {
        try {
            userService.modify(userVo);
            return ResponseResult.success("修改用户信息成功");
        } catch (UserExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 修改用户邮箱或者密码
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/modifyEmailOrPassword")
    @ResponseBody
    public ResponseResult modifyEmailOrPassword(UserVo userVo) {
        ResponseResult result = null;
        try {
            if (userVo.getEmail() != null) {
                userService.modifyEmail(userVo);
                result = ResponseResult.success("邮箱修改成功");
            } else if (userVo.getNew_password() != null) {
                userService.modifyPassword(userVo);
                result = ResponseResult.success("密码修改成功");
            } else {
                result = ResponseResult.fail("参数错误,请重试");
            }
        } catch (UserExistException e) {
            result = ResponseResult.fail(e.getMessage());
        }
        return result;
    }

    /**
     * 会员充值接口
     * @param userAccount
     * @param rechargeNum
     * @return
     */
    @RequestMapping("/recharge")
    @ResponseBody
    public ResponseResult recharge(@Param("userAccount") String userAccount,@Param("rechargeNum") Double rechargeNum) {
        try {
            userService.recharge(userAccount,rechargeNum);
            return ResponseResult.success("充值成功");
        } catch (UserExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @GetMapping("/userRegister")
    @ResponseBody
    public ResponseResult userRegister(UserVo userVo){
        try {
            userService.add(userVo);
            return ResponseResult.success();
        } catch (UserExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(UserVo userVo){
        UserVo user= null;
        try {
            user = userService.login(userVo);
            return ResponseResult.success(user);
        } catch (UserExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
