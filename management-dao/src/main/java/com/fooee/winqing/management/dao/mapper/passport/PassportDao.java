package com.fooee.winqing.management.dao.mapper.passport;

import com.fooee.winqing.management.dao.vdo.passport.*;

import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/10
 */
public interface PassportDao {

    /**
     * 获取用户所有所属组织机构，包含岗位和部门
     * 按照设计用户应该不能属于部门，只能属于岗位
     * @param manageUserQc
     * @return
     */
    List<ManageDepartmentDo> getAllDepartmentByUser(ManageUserQc manageUserQc);

    /**
     * 递归获取某个组织机构的所有父级机构
     * @param manageDepartmentDo
     * @return
     */
    List<ManageDepartmentDo> getParentDepartments(ManageDepartmentDo manageDepartmentDo);

    /**
     * 获取组织机构的所有功能列表
     * @param manageDepartmentDos
     * @return
     */
    Set<ManageFunctionDo> getAllFunctionByDepartments(List<ManageDepartmentDo> manageDepartmentDos);

    /**
     * 管理用户登陆
     * @param manageUserQc
     * @return
     */
    ManageUserDo selectUserForLogin(ManageUserQc manageUserQc);
}
