package com.fooee.winqing.management.service.inf.user;

import com.fooee.winqing.bean.user.UserInfoQc;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/10/26
 */
public interface UserService {

    /**
     * 禁用用户
     */
    void enable(UserInfoQc userInfoQc);
}
