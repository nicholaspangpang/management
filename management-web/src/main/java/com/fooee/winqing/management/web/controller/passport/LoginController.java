package com.fooee.winqing.management.web.controller.passport;

import com.fooee.commons.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/8
 */
@Controller
public class LoginController{

    @RequestMapping("login")
    String login(){

        return "login";
    }

    @RequestMapping("logout")
    String logout(HttpServletRequest request){
        request.getSession().setAttribute("manageUserSession",null);
        return "login";
    }
}
