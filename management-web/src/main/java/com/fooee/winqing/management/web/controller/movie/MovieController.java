package com.fooee.winqing.management.web.controller.movie;

import com.fooee.commons.util.XssUtil;
import com.fooee.winqing.bean.book.BookDescriptionInfoDo;
import com.fooee.winqing.bean.book.BookDescriptionInfoQc;
import com.fooee.winqing.bean.book.BookInfoDo;
import com.fooee.winqing.bean.book.BookInfoQc;
import com.fooee.winqing.bean.movie.MovieDescriptionInfoDo;
import com.fooee.winqing.bean.movie.MovieDescriptionInfoQc;
import com.fooee.winqing.bean.movie.MovieInfoDo;
import com.fooee.winqing.bean.movie.MovieInfoQc;
import com.fooee.winqing.management.service.inf.book.BookService;
import com.fooee.winqing.management.service.inf.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/12
 */
@Controller
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping("manage")
    String manage(){
        return "movie/manage";
    }

    @RequestMapping("add")
    String add(){
        return "movie/add";
    }

    @RequestMapping("update/{id}")
    String update(@PathVariable Long id, HttpServletRequest request){
        //读取基本信息
        MovieInfoQc movieInfoQc = new MovieInfoQc();
        movieInfoQc.setId(id);
        MovieInfoDo movieInfoDo = movieService.getBaseInfo(movieInfoQc);

        //读取图书描述信息
        MovieDescriptionInfoQc movieDescriptionInfoQc = new MovieDescriptionInfoQc();
        movieDescriptionInfoQc.setMovieId(id);
        MovieDescriptionInfoDo movieDescriptionInfoDo = movieService.getDescriptionInfo(movieDescriptionInfoQc);

        //处理大字段前端textarea展示
        movieDescriptionInfoDo.setPlotBrief(XssUtil.encodeBr(movieDescriptionInfoDo.getPlotBrief()));
        movieDescriptionInfoDo.setEditorRecommendInfo(XssUtil.encodeBr(movieDescriptionInfoDo.getEditorRecommendInfo()));

        request.setAttribute("movieInfo",movieInfoDo);
        request.setAttribute("movieDesc",movieDescriptionInfoDo);

        return "movie/update";
    }
}
