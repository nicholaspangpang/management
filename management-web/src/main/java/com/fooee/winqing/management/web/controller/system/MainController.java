package com.fooee.winqing.management.web.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/6
 */
@Controller
public class MainController {

    @RequestMapping("main")
    String main(){

        return "main";
    }
}
