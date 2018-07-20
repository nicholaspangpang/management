package com.fooee.winqing.management.dao.vdo.book;

import com.fooee.commons.compontent.query.QueryResult;

/**
 * Created by nicholas on 2017/9/16.
 * @author pangzhenhua
 */
public class BookDescriptionInfoDo extends QueryResult{
    /**
     *
     */
    private Integer id;

    /**
     * 图书ID
     */
    private Integer bookId;

    /**
     * 目录信息
     */
    private String catelogInfo;

    /**
     * 内容简介
     */
    private String contentBrief;

    /**
     * 作者简介
     */
    private String authorBrief;

    /**
     * 编辑推荐信息
     */
    private String editorRecommendInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getCatelogInfo() {
        return catelogInfo;
    }

    public void setCatelogInfo(String catelogInfo) {
        this.catelogInfo = catelogInfo;
    }

    public String getContentBrief() {
        return contentBrief;
    }

    public void setContentBrief(String contentBrief) {
        this.contentBrief = contentBrief;
    }

    public String getAuthorBrief() {
        return authorBrief;
    }

    public void setAuthorBrief(String authorBrief) {
        this.authorBrief = authorBrief;
    }

    public String getEditorRecommendInfo() {
        return editorRecommendInfo;
    }

    public void setEditorRecommendInfo(String editorRecommendInfo) {
        this.editorRecommendInfo = editorRecommendInfo;
    }
}
