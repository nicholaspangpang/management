package com.fooee.winqing.management.service.inf.book;

import com.fooee.winqing.management.dao.vdo.book.BookDescriptionInfoDo;
import com.fooee.winqing.management.dao.vdo.book.BookDescriptionInfoQc;
import com.fooee.winqing.management.dao.vdo.book.BookInfoDo;
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

    /**
     * 获取图书基本信息
     * @return
     */
    BookInfoDo getBookinfo(BookInfoQc bookInfoQc);

    /**
     * 获取图书描述信息
     * @param bookDescriptionInfoQc
     * @return
     */
    BookDescriptionInfoDo getBookDescriptionInfo(BookDescriptionInfoQc bookDescriptionInfoQc);

}