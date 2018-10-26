package com.fooee.winqing.management.service.impl.user;

import com.fooee.commons.service.user.inf.UserCommonService;
import com.fooee.winqing.bean.user.UserInfoDo;
import com.fooee.winqing.bean.user.UserInfoQc;
import com.fooee.winqing.management.service.inf.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/10/26
 */
@Service
public class UserServiceImpl implements UserService{


    @Autowired
    UserCommonService userCommonService;

    /**
     * 禁用用户
     * @param userInfoQc
     */
    @Override
    public void unable(UserInfoQc userInfoQc) {
        /**
         * 校验参数
         */

        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setId(userInfoQc.getId());
        userInfoDo.setStatusCode(1L);
        userCommonService.update(userInfoDo);
    }
}
