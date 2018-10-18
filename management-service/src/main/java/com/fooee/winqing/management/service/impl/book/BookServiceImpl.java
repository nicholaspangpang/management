package com.fooee.winqing.management.service.impl.book;

import com.fooee.commons.dao.vdo.upload.UploadFileVo;
import com.fooee.commons.service.upload.inf.UploadService;
import com.fooee.winqing.bean.book.BookDescriptionInfoDo;
import com.fooee.winqing.bean.book.BookDescriptionInfoQc;
import com.fooee.winqing.bean.book.BookInfoDo;
import com.fooee.winqing.bean.book.BookInfoQc;
import com.fooee.winqing.management.service.inf.book.BookService;
import com.fooee.winqing.management.service.micro.inf.book.BookDescriptionInfoService;
import com.fooee.winqing.management.service.micro.inf.book.BookInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/19
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookInfoService bookInfoService;

    @Autowired
    BookDescriptionInfoService bookDescriptionInfoService;

    @Autowired
    UploadService uploadService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BookInfoQc bookInfoQc, BookDescriptionInfoQc bookDescriptionInfoQc) {
        /**
         * 如果没有图片则不更新
         */
        BookInfoDo bookInfoDo = new BookInfoDo();
        BeanUtils.copyProperties(bookInfoQc,bookInfoDo);

        //上传图片
        if(null != bookInfoQc.getFile()){
            UploadFileVo uploadFileVo = new UploadFileVo();
            uploadFileVo.setMultipartFile(bookInfoQc.getFile());
            uploadFileVo = uploadService.upload(uploadFileVo);
            bookInfoDo.setPictureAddress(uploadFileVo.getFileUrl());
        }

        //更新图书基本信息
        bookInfoService.update(bookInfoDo);

        //保存图书描述信息
        BookDescriptionInfoDo bookDescriptionInfoDo = new BookDescriptionInfoDo();
        BeanUtils.copyProperties(bookDescriptionInfoQc,bookDescriptionInfoDo);
        bookDescriptionInfoDo.setBookId(bookInfoDo.getId());
        bookDescriptionInfoService.update(bookDescriptionInfoDo);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(BookInfoQc bookInfoQc, BookDescriptionInfoQc bookDescriptionInfoQc) {
        /**
         * 上传图片
         * 保存信息
         */

        BookInfoDo bookInfoDo = new BookInfoDo();
        BeanUtils.copyProperties(bookInfoQc,bookInfoDo);

        //上传图片
        if(null != bookInfoQc.getFile()){
            UploadFileVo uploadFileVo = new UploadFileVo();
            uploadFileVo.setMultipartFile(bookInfoQc.getFile());
            uploadFileVo = uploadService.upload(uploadFileVo);
            bookInfoDo.setPictureAddress(uploadFileVo.getFileUrl());
        }

        //保存图书基本信息
        bookInfoService.insert(bookInfoDo);
        Long bookId = bookInfoDo.getId();

        //保存图书描述信息
        BookDescriptionInfoDo bookDescriptionInfoDo = new BookDescriptionInfoDo();
        BeanUtils.copyProperties(bookDescriptionInfoQc,bookDescriptionInfoDo);
        bookDescriptionInfoDo.setBookId(bookId);
        bookDescriptionInfoService.insert(bookDescriptionInfoDo);
    }

    @Override
    public BookInfoDo getBookinfo(BookInfoQc bookInfoQc) {
        return bookInfoService.select(bookInfoQc);
    }

    @Override
    public BookDescriptionInfoDo getBookDescriptionInfo(BookDescriptionInfoQc bookDescriptionInfoQc) {
        return bookDescriptionInfoService.select(bookDescriptionInfoQc);
    }
}
