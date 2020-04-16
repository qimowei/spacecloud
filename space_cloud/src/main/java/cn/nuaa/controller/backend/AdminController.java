package cn.nuaa.controller.backend;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.exception.AdminExistException;
import cn.nuaa.common.exception.UserExistException;
import cn.nuaa.entity.Admin;
import cn.nuaa.service.AdminService;
import cn.nuaa.vo.AdminVo;
import cn.nuaa.vo.UserVo;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * @Author: wpc
 * @Date: 2020/1/31 20:38
 * @Description: <描述>
 */
@Controller
@RequestMapping("/backend/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 后台管理员登录
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "backend/main";
    }

    /**
     * 根据adminAccount查找管理员信息
     *
     * @param adminAccount
     * @return
     */
    @RequestMapping(value = "/findByAdminAccount",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult findByAdminAccount(String adminAccount) {
        if(ObjectUtils.isEmpty(adminAccount)){
            adminAccount="admin";
        }
        Admin admin = adminService.findByAdminAccount(adminAccount);
        return ResponseResult.success(Arrays.asList(admin));
    }

    /**
     * 修改管理员信息
     *
     * @param adminVo
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(AdminVo adminVo) {
        try {
            adminService.modify(adminVo);
            return ResponseResult.success();
        } catch (AdminExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 修改管理员密码
     *
     * @param adminVo
     * @return
     */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public ResponseResult modifyPassword(AdminVo adminVo) {
        try {
            adminService.modifyPassword(adminVo);
            return ResponseResult.success();
        } catch (AdminExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @GetMapping("/register")
    @ResponseBody
    public ResponseResult register(AdminVo adminVo){
        try {
            adminService.add(adminVo);
            return ResponseResult.success();
        } catch (AdminExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(AdminVo adminVo){
        AdminVo admin= null;
        try {
            admin = adminService.login(adminVo);
            return ResponseResult.success(admin);
        } catch (AdminExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
