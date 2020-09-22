package com.swee.model.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BizInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("访问接口之前");
        String uri = request.getRequestURI();
        log.info(uri+"被拦截");
        if (StringUtils.isNotBlank(uri) && (
                uri.contains("/api")
                ||uri.contains("/kafka")
                ||uri.contains("/department")
                )) {
            return true;
        }
        return false;
    }
}
