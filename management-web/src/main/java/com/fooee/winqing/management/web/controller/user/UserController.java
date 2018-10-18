package com.fooee.winqing.management.web.controller.user;

import com.fooee.winqing.bean.book.BookDescriptionInfoDo;
import com.fooee.winqing.bean.book.BookDescriptionInfoQc;
import com.fooee.winqing.bean.book.BookInfoDo;
import com.fooee.winqing.bean.book.BookInfoQc;
import com.fooee.winqing.management.service.inf.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/12
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("manage")
    String manage(){
        return "user/manage";
    }

    @RequestMapping("add")
    String add(){
        return "book/add";
    }

}
