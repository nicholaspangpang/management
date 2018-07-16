package com.fooee.winqing.management.service.inf.passport;

import com.fooee.winqing.management.dao.vdo.passport.ManageUserVo;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/10
 */
public interface PassportService {

    /**
     * 获取用户所有的组织机构（包含多岗位和上级部门）
     * @param manageUserDo
     * @return
     */
//    List<ManageDepartmentDo> getAllDepartment(ManageUserDo manageUserDo);

    /**
     * 获取组织机构所有的树形功能列表
     * @param manageDepartmentDos
     * @return
     */
//    List<ManageFunctionDo> getAllFunction(List<ManageDepartmentDo> manageDepartmentDos);

    /**
     * 管理用户登陆
     * @param manageUserVo
     */
    ManageUserVo manageUserLogin(ManageUserVo manageUserVo);
}
