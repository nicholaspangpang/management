package com.fooee.winqing.management.dao.mapper.movie;

import com.fooee.commons.dao.BaseDao;
import com.fooee.winqing.bean.book.BookInfoDo;
import com.fooee.winqing.bean.comment.ScorePercentageVo;
import com.fooee.winqing.bean.list.ListInfoQc;
import com.fooee.winqing.bean.movie.MovieInfoDo;
import com.fooee.winqing.bean.movie.MovieInfoQc;
import com.fooee.winqing.bean.tag.TagInfoQc;

import java.util.List;

/**
 * @author pangzhenhua
 * Created on 2016/11/29.
 */
public interface MovieInfoDao extends BaseDao<MovieInfoDo,MovieInfoQc>{

    /**
     * 获取对象评分占比
     * @param scorePercentageVo
     * @return
     */
    ScorePercentageVo getScorePercentage(ScorePercentageVo scorePercentageVo);

    /**
     * 重新计算设置图书总评分
     * @param movieInfoDo
     */
    void resetScore(MovieInfoDo movieInfoDo);

    /**
     * 获取首页新书
     * @return
     */
    List<BookInfoDo> getNewBook();

    /**
     * 获取首页热书
     * @return
     */
    List<BookInfoDo> getHotBook();

    /**
     * 获取图书列表根据tag
     * @return
     */
    List<MovieInfoDo> getBooksByTagByPage(TagInfoQc tagInfoQc);

    /**
     * 获取书单下的图书列表
     * @return
     */
    List<BookInfoDo> getBooksByListByPage(ListInfoQc listInfoQc);


}
