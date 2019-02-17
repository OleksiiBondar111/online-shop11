package com.bonolex.onlineshop.web.auth;


import com.bonolex.onlineshop.entity.Session;
import com.bonolex.onlineshop.service.DefaultSecurityService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by OBondar on 16.02.2019.
 */
public class AuthFilter implements Filter {

    private DefaultSecurityService defaultSecurityService;

    public AuthFilter(DefaultSecurityService defaultSecurityService) {
        this.defaultSecurityService = defaultSecurityService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (isTokenValid(httpServletRequest, defaultSecurityService)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }

    public static boolean isTokenValid(HttpServletRequest request, DefaultSecurityService defaultSecurityService) {
        Cookie[] cookies = request.getCookies();
        boolean isValid = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user-token".equals(cookie.getName())) {
                    Session session = defaultSecurityService.getSession(cookie.getValue());
                    if (session != null) {
                        isValid = true;
                    }
                    break;
                }
            }
        }
        return isValid;
    }
}
