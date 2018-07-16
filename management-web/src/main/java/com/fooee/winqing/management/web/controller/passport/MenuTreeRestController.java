package com.fooee.winqing.management.web.controller.passport;

import com.fooee.commons.web.vo.JsonResult;
import com.fooee.winqing.management.dao.vdo.passport.ManageFunctionDo;
import com.fooee.winqing.management.dao.vdo.passport.ManageUserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/10
 */
@RestController
public class MenuTreeRestController {

    @RequestMapping("menuTree")
    Object menuTree(HttpServletRequest request){
        JsonResult jsonResult = new JsonResult();

        ManageUserVo manageUserSession = (ManageUserVo)request.getSession().getAttribute("manageUserSession");
        List<ManageFunctionDo> menuTree = manageUserSession.getManageFcuntionTrees();

        jsonResult.setData(menuTree);

        return jsonResult;
    }

}
