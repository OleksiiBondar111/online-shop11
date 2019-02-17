package com.bonolex.onlineshop.web.servlets.product;

import com.bonolex.onlineshop.entity.Product;
import com.bonolex.onlineshop.jdbc.dao.ProductDAO;
import com.bonolex.onlineshop.service.DefaultProductService;
import com.bonolex.onlineshop.service.DefaultUserService;
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
public class ViewProductServlet extends HttpServlet {
    private DefaultProductService productService;

    public ViewProductServlet(DefaultProductService defaultProductService) {
        this.productService = defaultProductService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("products", products);
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        String page = pageGenerator.getPage("product.html", parameters);
        resp.getWriter().write(page);

    }
}
