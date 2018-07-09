package com.fooee.winqing.management.web.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/8
 */
@Controller
public class LoginController {

    @RequestMapping("login")
    String login(){

        return "login";
    }

    @RequestMapping("logout")
    String logout(){

        return "login";
    }
}
