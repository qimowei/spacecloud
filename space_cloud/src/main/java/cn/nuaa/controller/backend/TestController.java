package cn.nuaa.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: wpc
 * @Date: 2020/2/18 14:50
 * @Description: <描述>
 */
@Controller
@RequestMapping("/backend/test")
public class TestController {

    /**
     * 表格的测试
     */
    @RequestMapping("/table")
    public String table() {
        return "backend/table";
    }

    /**
     * 表单的测试
     */
    @RequestMapping("/form")
    public String form(){
        return "backend/form";
    }
}
