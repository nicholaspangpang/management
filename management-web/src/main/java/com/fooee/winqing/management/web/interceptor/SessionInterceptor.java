package com.fooee.winqing.management.web.interceptor;

import com.fooee.winqing.management.dao.vdo.passport.ManageUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author pangzhenhua
 * @date 2018/7/8
 */
public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SessionInterceptor.class);

    public SessionInterceptor() {
    }

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
    }

    public Cookie getCookieByName(HttpServletRequest request, String name) {
        Map cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        HashMap cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if(null != cookies) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String requestUri = request.getRequestURI();
        StringBuffer requestUrl = request.getRequestURL();
        String contextPath = request.getContextPath();
        log.info("requestUri:" + requestUri);
        log.info("contextPath:" + contextPath);
        if("/singleLogin".equals(requestUri)) {
            return true;
        } else {
            ManageUserVo manageUserSession = (ManageUserVo)request.getSession().getAttribute("manageUserSession");
            if(null == manageUserSession) {
                request.getSession().setAttribute("manageUserSession", (Object)null);
                response.sendRedirect("/login");
                return false;
            } else {
                return true;
            }
        }
    }

}
