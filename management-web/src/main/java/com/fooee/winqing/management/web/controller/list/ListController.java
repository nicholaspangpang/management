package com.fooee.winqing.management.web.controller.list;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/10/29
 */
@Controller
@RequestMapping("list")
public class ListController {

    @RequestMapping("manage")
    String manage(){
        return "list/manage";
    }

}
