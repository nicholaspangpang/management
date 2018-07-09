package com.fooee.winqing.management.web.controller.system;

import com.fooee.commons.web.vo.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/8
 */
@RestController
public class LoginRestController {

    @RequestMapping(value = "login",method = RequestMethod.POST)
    Object login(){
        JsonResult jsonResult = new JsonResult();

        return jsonResult;
    }
}
