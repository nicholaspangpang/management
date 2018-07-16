package com.fooee.winqing.management.service.impl.passport;

import com.fooee.commons.exception.PageException;
import com.fooee.commons.util.MD5Util;
import com.fooee.winqing.management.dao.mapper.passport.PassportDao;
import com.fooee.winqing.management.dao.vdo.passport.*;
import com.fooee.winqing.management.service.inf.passport.PassportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/10
 */
@Service
public class PassportServiceImpl implements PassportService{

    @Autowired
    PassportDao passportDao;

    @Override
    public ManageUserVo manageUserLogin(ManageUserVo manageUserVo) {
        /**
         * 验证用户密码
         * 读取用户所属的多个组织机构，包含岗位和部门（设计上用户只能属于岗位，不能属于部门）
         * 读取多个组织机构的父级组织机构，最终形成用户的全部组织机构
         * 循环读取用户组织机构所有的功能列表，用set不能重复
         * 将用户所有功能转成树形list
         * 存储用户信息和菜单信息到session(这个放到controller去做)
         */

        ManageUserQc manageUserQc = new ManageUserQc();
        manageUserQc.setUserName(manageUserVo.getUserName());
        manageUserQc.setUserPass(MD5Util.string2MD5(manageUserVo.getUserPass()));
        ManageUserDo manageUserDo = passportDao.selectUserForLogin(manageUserQc);

        if(null == manageUserDo){
            throw new PageException("用户不存在或密码错误");
        }

        //获取用户所属的多个组织机构
        manageUserQc.setId(manageUserDo.getId());
        List<ManageDepartmentDo> manageDepartmentDos = passportDao.getAllDepartmentByUser(manageUserQc);

        //循环获取用户组织机构的父级机构，最终形成用户的全部组织机构
        List<ManageDepartmentDo> allManageDepartmentDos = new ArrayList<ManageDepartmentDo>();
        for(ManageDepartmentDo dept : manageDepartmentDos){
            allManageDepartmentDos.addAll(passportDao.getParentDepartments(dept));
        }

        //循环读取用户组织机构所有的功能列表，用set不能重复
        Set<ManageFunctionDo> manageFunctionDos = new HashSet<ManageFunctionDo>();
        manageFunctionDos = passportDao.getAllFunctionByDepartments(allManageDepartmentDos);

        //将用户所有功能转成树形list
        List<ManageFunctionDo> manageFunctionDosTree = convertMenuTree(manageFunctionDos);

        BeanUtils.copyProperties(manageUserDo,manageUserVo);
        manageUserVo.setManageFcuntionTrees(manageFunctionDosTree);

        return manageUserVo;
    }

    /**
     * 将用户功能列表转换为树形
     * @param manageFunctionDos
     * @return
     */
    List<ManageFunctionDo> convertMenuTree(Set<ManageFunctionDo> manageFunctionDos){
        List<ManageFunctionDo> tree = new ArrayList<ManageFunctionDo>();
        for(ManageFunctionDo item : manageFunctionDos){
            if(item.getParentId() == 0){
                item.setChildren(getChildren(item,manageFunctionDos));
                tree.add(item);
            }
        }
        return tree;
    }


    List<ManageFunctionDo> getChildren(ManageFunctionDo currFunction , Set<ManageFunctionDo> allFunction){
        List<ManageFunctionDo> result = new ArrayList<ManageFunctionDo>();
        for(ManageFunctionDo item : allFunction){
            if(currFunction.getId().equals(item.getParentId())){
                item.setChildren(getChildren(item, allFunction));
                result.add(item);
            }
        }
        return result;
    }
}
