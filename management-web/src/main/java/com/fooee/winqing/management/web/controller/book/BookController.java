package com.fooee.winqing.management.web.controller.book;

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
@RequestMapping("book")
public class BookController{

    @Autowired
    BookService bookService;

    @RequestMapping("manage")
    String manage(){
        return "book/manage";
    }

    @RequestMapping("add")
    String add(){
        return "book/add";
    }

    @RequestMapping("update/{id}")
    String update(@PathVariable Long id, HttpServletRequest request){
        //读取图书基本信息
        BookInfoQc bookInfoQc = new BookInfoQc();
        bookInfoQc.setId(id);
        BookInfoDo bookInfoDo = bookService.getBookinfo(bookInfoQc);

        //读取图书描述信息
        BookDescriptionInfoQc bookDescriptionInfoQc = new BookDescriptionInfoQc();
        bookDescriptionInfoQc.setBookId(id);
        BookDescriptionInfoDo bookDescriptionInfoDo = bookService.getBookDescriptionInfo(bookDescriptionInfoQc);

        request.setAttribute("bookInfo",bookInfoDo);
        request.setAttribute("bookDesc",bookDescriptionInfoDo);

        return "book/update";
    }
}
