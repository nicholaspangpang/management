package com.fooee.winqing.management.web.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/10/24
 */
@Controller
@RequestMapping("user")
public class CommentController {

    @RequestMapping("manage")
    String manage(){
        return "user/manage";
    }
}
