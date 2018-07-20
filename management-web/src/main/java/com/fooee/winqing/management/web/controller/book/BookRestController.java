package com.fooee.winqing.management.web.controller.book;

import com.fooee.commons.exception.PageException;
import com.fooee.commons.web.vo.JsonResult;
import com.fooee.winqing.management.dao.vdo.book.BookDescriptionInfoQc;
import com.fooee.winqing.management.dao.vdo.book.BookInfoQc;
import com.fooee.winqing.management.service.inf.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/18
 */
@RequestMapping("book")
@Controller
//@RestController
public class BookRestController{

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    JsonResult add(@Valid BookInfoQc bookInfoQc, BindingResult  bindingResult){
        JsonResult jsonResult = new JsonResult();

        if (bindingResult.hasErrors()){
            throw new PageException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

//        bookService.add(bookInfoQc, bookDescriptionInfoQc);

        return jsonResult;
    }
}
