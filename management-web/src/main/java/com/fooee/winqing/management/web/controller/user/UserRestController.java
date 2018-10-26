package com.fooee.winqing.management.web.controller.user;

import com.fooee.commons.web.vo.JsonResult;
import com.fooee.winqing.bean.user.UserInfoQc;
import com.fooee.winqing.management.service.inf.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/10/26
 */
@RestController
@RequestMapping("user")
public class UserRestController {

    @Autowired
    UserService userService;

    /**
     * 禁用用户
     * @return
     */
    @RequestMapping("unable")
    JsonResult unable(@RequestBody List<UserInfoQc> userInfoQcs){
        JsonResult jsonResult = new JsonResult();

        for (UserInfoQc item : userInfoQcs){
            userService.unable(item);
        }

        return jsonResult;
    }
}
