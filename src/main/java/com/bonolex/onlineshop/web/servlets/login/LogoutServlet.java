package com.bonolex.onlineshop.web.servlets.login;

import com.bonolex.onlineshop.service.DefaultSecurityService;
import com.bonolex.onlineshop.web.auth.TokenUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by OBondar on 16.02.2019.
 */
public class LogoutServlet extends HttpServlet {
    private DefaultSecurityService defaultSecurityService;

    public LogoutServlet(DefaultSecurityService defaultSecurityService) {
        this.defaultSecurityService = defaultSecurityService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Cookie logoutCookie = TokenUtils.logout(request, defaultSecurityService);
        response.addCookie(logoutCookie);
        response.sendRedirect("/login");
    }
}