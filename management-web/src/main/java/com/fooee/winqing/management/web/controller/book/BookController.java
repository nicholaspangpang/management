package com.fooee.winqing.management.web.controller.book;

import com.fooee.commons.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/12
 */
@Controller
@RequestMapping("book")
public class BookController extends BaseController{

    @RequestMapping("manage")
    String manage(){
        return "book/manage";
    }
}
