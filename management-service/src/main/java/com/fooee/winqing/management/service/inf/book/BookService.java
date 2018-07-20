package com.fooee.winqing.management.service.inf.book;

import com.fooee.winqing.management.dao.vdo.book.BookDescriptionInfoQc;
import com.fooee.winqing.management.dao.vdo.book.BookInfoQc;

/**
 * 图书业务服务
 * @author pangzhenhua
 * @date 2018/7/19
 */
public interface BookService {

    /**
     * 录入图书
     */
    void add(BookInfoQc bookInfoQc, BookDescriptionInfoQc bookDescriptionInfoQc);

}
