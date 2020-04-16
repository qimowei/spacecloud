package cn.nuaa.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: wpc
 * @Date: 2020/3/1 15:53
 * @Description: <描述>
 */
@Controller
@RequestMapping("/backend")
public class BackendUrlController {

    /**
     * 后台主main的url
     * @return
     */
    @RequestMapping("/main")
    public String main(){
        return "backend/main";
    }

    /**
     * home的url
     * @return
     */
    @RequestMapping("/home")
    public String home(){
        return "backend/home";
    }

    /**
     * nameManager名称管理url
     * @return
     */
    @RequestMapping("/nameManager/show")
    public String nameManagerShow() {
        return "backend/nameManager";
    }

    /**
     * paramManager参数管理url
     * @return
     */
    @RequestMapping("/paramManager/show")
    public String paramManagerShow(){
        return "backend/paramManager";
    }

    /**
     * roomManager机房管理url
     * @return
     */
    @RequestMapping("/roomManager/show")
    public String roomManagerShow(){
        return "backend/roomManager";
    }

    /**
     * priceManager价格管理url
     * @return
     */
    @RequestMapping("/priceManager/show")
    public String priceManagerShow(){
        return "backend/priceManager";
    }

    /**
     * SystemManager系统管理url
     * @return
     */
    @RequestMapping("/systemManager/show")
    public String SystemManagerShow(){
        return "backend/systemManager";
    }

    /**
     * adminData管理员资料url
     * @return
     */
    @RequestMapping("/adminData/show")
    public String adminDataShow(){
        return "backend/adminData";
    }

    /**
     * inviteCode邀请码url
     * @return
     */
    @RequestMapping("/inviteCode/show")
    public String inviteCodeShow(){
        return "backend/inviteCode";
    }
}
