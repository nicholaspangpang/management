package com.fooee.winqing.management.dao.vdo.book;

import com.fooee.commons.compontent.query.QueryCondition;

/**
 * Created by nicholas on 2017/9/16.
 * @author pangzhenhua
 */
public class BookDescriptionInfoQc extends QueryCondition {
    /**
     *
     */
    private Long id;

    /**
     * 文青ID
     */
    private Long bookId;

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

    /**
     * 媒体评论信息
     */
    private String mediaCommentInfo;

    public String getMediaCommentInfo() {
        return mediaCommentInfo;
    }

    public void setMediaCommentInfo(String mediaCommentInfo) {
        this.mediaCommentInfo = mediaCommentInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
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
