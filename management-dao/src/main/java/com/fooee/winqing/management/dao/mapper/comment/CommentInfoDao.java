package com.fooee.winqing.management.dao.mapper.comment;

import com.fooee.commons.dao.BaseDao;
import com.fooee.winqing.bean.comment.CommentInfoDo;
import com.fooee.winqing.bean.comment.CommentInfoQc;
import com.fooee.winqing.bean.comment.CommentInfoVo;

import java.util.List;

/**
 * Created by pangzhenhua on 2016/12/30.
 */
public interface CommentInfoDao extends BaseDao<CommentInfoDo,CommentInfoQc>{

    /**
     * 通过图书ID查找评论列表
     * @param commentInfoQc
     * @return
     */
    List<CommentInfoVo> getCommentsByObjIdByPage(CommentInfoQc commentInfoQc);

    /**
     * 更新某人对某书的全部评论评分
     */
    void updateScoreNumberByUserAndBook(CommentInfoQc commentInfoQc);

    /**
     * 通过评论ID查找前端需要的评论信息
     * @param commentInfoQc
     * @return
     */
    CommentInfoVo getCommentVoById(CommentInfoQc commentInfoQc);

    /**
     * 赞同评论数加一
     * @param commentInfoDo
     */
    void agreeComment(CommentInfoDo commentInfoDo);

    /**
     * 获取评论的点赞用户列表（全部获取）
     * @param commentInfoQc
     * @return
     */
    List<CommentInfoVo> getAgreeUsers(CommentInfoQc commentInfoQc);
}
