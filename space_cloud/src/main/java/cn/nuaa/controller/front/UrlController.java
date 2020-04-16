package cn.nuaa.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: wpc
 * @Date: 2020/3/3 17:44
 * @Description: <描述>
 */
@Controller
public class UrlController {

    /**跳转到注册页面*/
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**跳转到登录页面*/
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
