package com.fooee.winqing.management.web.controller.book;

import com.fooee.commons.exception.PageException;
import com.fooee.commons.web.vo.JsonResult;
import com.fooee.winqing.bean.book.BookDescriptionInfoQc;
import com.fooee.winqing.bean.book.BookInfoQc;
import com.fooee.winqing.management.service.inf.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/18
 */
@RequestMapping("book")
@RestController
public class BookRestController{

    @Autowired
    BookService bookService;

    /**
     * 新增图书
     * @param bookInfoQc
     * @param bindingResult
     * @param bookDescriptionInfoQc
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    JsonResult add(BookInfoQc bookInfoQc, BindingResult bindingResult, BookDescriptionInfoQc bookDescriptionInfoQc){
        JsonResult jsonResult = new JsonResult();

        if (bindingResult.hasErrors()){
            throw new PageException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        bookService.add(bookInfoQc, bookDescriptionInfoQc);

        return jsonResult;
    }

    /**
     * 图书信息更新
     * @return
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    JsonResult update(BookInfoQc bookInfoQc, BindingResult  bindingResult, BookDescriptionInfoQc bookDescriptionInfoQc){
        JsonResult jsonResult = new JsonResult();

        if (bindingResult.hasErrors()){
            throw new PageException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        bookService.update(bookInfoQc, bookDescriptionInfoQc);

        return jsonResult;
    }

    /**
     * 启用禁用图书
     * @param bookInfoQcs
     * @return
     */
    @RequestMapping(value="enable", method = RequestMethod.POST)
    JsonResult enable(@RequestBody List<BookInfoQc> bookInfoQcs){
        JsonResult jsonResult = new JsonResult();

        for (BookInfoQc item : bookInfoQcs){
            bookService.enable(item);
        }

        return jsonResult;
    }
}
