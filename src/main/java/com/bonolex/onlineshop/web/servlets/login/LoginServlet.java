package com.bonolex.onlineshop.web.servlets.login;

import com.bonolex.onlineshop.entity.Session;
import com.bonolex.onlineshop.service.DefaultSecurityService;
import com.bonolex.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OBondar on 14.02.2019.
 */
public class LoginServlet extends HttpServlet {
    private DefaultSecurityService defaultSecurityService;

    public LoginServlet(DefaultSecurityService defaultSecurityService) {
        this.defaultSecurityService = defaultSecurityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("login.html");
        resp.getWriter().write(page);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Session session = defaultSecurityService.login(login, password);

        if (session == null) {
            Map<String, Object> pageVariables = new HashMap();
            pageVariables.put("message", "Wrong credentials");
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write(PageGenerator.instance().getPage("login.html", pageVariables));
        } else {
            Cookie cookie = new Cookie("user-token", session.getToken());
            cookie.setMaxAge(60*60*2);
            resp.addCookie(cookie);
            resp.sendRedirect("/products");
        }
    }
}

