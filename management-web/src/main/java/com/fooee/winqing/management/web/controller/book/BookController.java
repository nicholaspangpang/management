package com.fooee.winqing.management.web.controller.book;

import com.fooee.commons.web.controller.BaseController;
import com.fooee.commons.web.vo.JsonResult;
import com.fooee.winqing.management.dao.vdo.book.BookInfoDo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/12
 */
@Controller
@RequestMapping("book")
public class BookController{

    @RequestMapping("manage")
    String manage(){
        return "book/manage";
    }

    @RequestMapping("add")
    String add(){
        return "book/add";
    }
}
