package com.fooee.winqing.management.service.impl.movie;

import com.fooee.commons.dao.vdo.upload.UploadFileVo;
import com.fooee.commons.service.upload.inf.UploadService;
import com.fooee.winqing.bean.movie.MovieDescriptionInfoDo;
import com.fooee.winqing.bean.movie.MovieDescriptionInfoQc;
import com.fooee.winqing.bean.movie.MovieInfoDo;
import com.fooee.winqing.bean.movie.MovieInfoQc;
import com.fooee.winqing.management.service.inf.movie.MovieService;
import com.fooee.winqing.management.service.micro.inf.movie.MovieDescriptionInfoService;
import com.fooee.winqing.management.service.micro.inf.movie.MovieInfoService;
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
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    MovieDescriptionInfoService movieDescriptionInfoService;

    @Autowired
    UploadService uploadService;

    @Override
    public void enable(MovieInfoQc movieInfoQc) {
        /**
         * 校验参数
         */

        MovieInfoDo movieInfoDo = new MovieInfoDo();
        movieInfoDo.setId(movieInfoQc.getId());
        movieInfoDo.setIsEnable(movieInfoQc.getIsEnable());
        movieInfoService.update(movieInfoDo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MovieInfoQc movieInfoQc, MovieDescriptionInfoQc movieDescriptionInfoQc) {
        /**
         * 如果没有图片则不更新
         */
        MovieInfoDo movieInfoDo = new MovieInfoDo();
        BeanUtils.copyProperties(movieInfoQc,movieInfoDo);

        //上传图片
        if(null != movieInfoQc.getFile()){
            UploadFileVo uploadFileVo = new UploadFileVo();
            uploadFileVo.setMultipartFile(movieInfoQc.getFile());
            uploadFileVo = uploadService.upload(uploadFileVo);
            movieInfoDo.setPictureAddress(uploadFileVo.getFileUrl());
        }

        //更新图书基本信息
        movieInfoService.update(movieInfoDo);

        //保存图书描述信息
        MovieDescriptionInfoDo movieDescriptionInfoDo = new MovieDescriptionInfoDo();
        BeanUtils.copyProperties(movieDescriptionInfoQc,movieDescriptionInfoDo);
        movieDescriptionInfoDo.setMovieId(movieInfoDo.getId());
        movieDescriptionInfoService.update(movieDescriptionInfoDo);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MovieInfoQc movieInfoQc, MovieDescriptionInfoQc movieDescriptionInfoQc) {
        /**
         * 上传图片
         * 保存信息
         */

        MovieInfoDo movieInfoDo = new MovieInfoDo();
        BeanUtils.copyProperties(movieInfoQc,movieInfoDo);

        //上传图片
        if(null != movieInfoQc.getFile()){
            UploadFileVo uploadFileVo = new UploadFileVo();
            uploadFileVo.setMultipartFile(movieInfoQc.getFile());
            uploadFileVo = uploadService.upload(uploadFileVo);
            movieInfoDo.setPictureAddress(uploadFileVo.getFileUrl());
        }

        //保存图书基本信息
        movieInfoService.insert(movieInfoDo);
        Long objectId = movieInfoDo.getId();

        //保存图书描述信息
        MovieDescriptionInfoDo movieDescriptionInfoDo = new MovieDescriptionInfoDo();
        BeanUtils.copyProperties(movieDescriptionInfoQc,movieDescriptionInfoDo);
        movieDescriptionInfoDo.setMovieId(objectId);
        movieDescriptionInfoService.insert(movieDescriptionInfoDo);
    }

    @Override
    public MovieInfoDo getBaseInfo(MovieInfoQc movieInfoQc) {
        return movieInfoService.select(movieInfoQc);
    }

    @Override
    public MovieDescriptionInfoDo getDescriptionInfo(MovieDescriptionInfoQc movieDescriptionInfoQc) {
        return movieDescriptionInfoService.select(movieDescriptionInfoQc);
    }


}
