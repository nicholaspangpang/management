package com.fooee.winqing.management.web.controller.passport;

import com.fooee.commons.web.controller.BaseController;
import com.fooee.commons.web.vo.JsonResult;
import com.fooee.winqing.management.dao.vdo.passport.ManageUserDo;
import com.fooee.winqing.management.dao.vdo.passport.ManageUserVo;
import com.fooee.winqing.management.service.inf.passport.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/8
 */
@Controller
public class LoginRestController{

    @Autowired
    PassportService passportService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    JsonResult login(@RequestBody ManageUserVo manageUserVo, HttpServletRequest request){
        JsonResult jsonResult = new JsonResult();

        ManageUserVo manageUserSession = passportService.manageUserLogin(manageUserVo);
        request.getSession().setAttribute("manageUserSession",manageUserSession);

        return jsonResult;
    }
}
