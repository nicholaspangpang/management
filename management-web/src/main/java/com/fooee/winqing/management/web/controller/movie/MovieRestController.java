package com.fooee.winqing.management.web.controller.movie;

import com.fooee.commons.exception.PageException;
import com.fooee.commons.web.vo.JsonResult;
import com.fooee.winqing.bean.movie.MovieDescriptionInfoQc;
import com.fooee.winqing.bean.movie.MovieInfoQc;
import com.fooee.winqing.management.service.inf.movie.MovieService;
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
@RequestMapping("movie")
@RestController
public class MovieRestController {

    @Autowired
    MovieService movieService;

    /**
     * 新增电影
     * @param movieInfoQc
     * @param bindingResult
     * @param movieDescriptionInfoQc
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    JsonResult add(MovieInfoQc movieInfoQc, BindingResult bindingResult, MovieDescriptionInfoQc movieDescriptionInfoQc){
        JsonResult jsonResult = new JsonResult();

        if (bindingResult.hasErrors()){
            throw new PageException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        movieService.add(movieInfoQc, movieDescriptionInfoQc);

        return jsonResult;
    }

    /**
     * 图书信息更新
     * @return
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    JsonResult update(MovieInfoQc movieInfoQc, BindingResult  bindingResult, MovieDescriptionInfoQc movieDescriptionInfoQc){
        JsonResult jsonResult = new JsonResult();

        if (bindingResult.hasErrors()){
            throw new PageException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        movieService.update(movieInfoQc, movieDescriptionInfoQc);

        return jsonResult;
    }

    /**
     * 启用禁用图书
     * @param movieInfoQcs
     * @return
     */
    @RequestMapping(value="enable", method = RequestMethod.POST)
    JsonResult enable(@RequestBody List<MovieInfoQc> movieInfoQcs){
        JsonResult jsonResult = new JsonResult();

        for (MovieInfoQc item : movieInfoQcs){
            movieService.enable(item);
        }

        return jsonResult;
    }
}
