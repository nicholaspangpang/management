package com.fooee.winqing.management.service.inf.movie;

import com.fooee.winqing.bean.movie.MovieDescriptionInfoDo;
import com.fooee.winqing.bean.movie.MovieDescriptionInfoQc;
import com.fooee.winqing.bean.movie.MovieInfoDo;
import com.fooee.winqing.bean.movie.MovieInfoQc;

/**
 * 电影业务服务
 * @author pangzhenhua
 * @date 2018/7/19
 */
public interface MovieService {

    /**
     * 新增电影
     */
    void add(MovieInfoQc movieInfoQc, MovieDescriptionInfoQc movieDescriptionInfoQc);

    /**
     * 更新电影
     */
    void update(MovieInfoQc movieInfoQc, MovieDescriptionInfoQc movieDescriptionInfoQc);

    /**
     * 获取基本信息
     * @return
     */
    MovieInfoDo getBaseInfo(MovieInfoQc movieInfoQc);

    /**
     * 获取描述信息
     * @param movieDescriptionInfoQc
     * @return
     */
    MovieDescriptionInfoDo getDescriptionInfo(MovieDescriptionInfoQc movieDescriptionInfoQc);

    /**
     * 启用禁用图书
     * @param movieInfoQc
     */
    void enable(MovieInfoQc movieInfoQc);
}
