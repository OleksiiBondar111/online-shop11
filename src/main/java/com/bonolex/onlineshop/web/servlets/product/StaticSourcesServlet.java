package com.bonolex.onlineshop.web.servlets.product;

import com.bonolex.onlineshop.entity.Product;
import com.bonolex.onlineshop.service.DefaultProductService;
import com.bonolex.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by OBondar on 14.02.2019.
 */
public class StaticSourcesServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        String page = pageGenerator.getPage("style.css");
        resp.getWriter().write(page);

    }
}
