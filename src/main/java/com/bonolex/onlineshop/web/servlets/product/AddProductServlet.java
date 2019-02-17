package com.bonolex.onlineshop.web.servlets.product;

import com.bonolex.onlineshop.entity.Product;
import com.bonolex.onlineshop.service.DefaultProductService;
import com.bonolex.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by OBondar on 16.02.2019.
 */
public class AddProductServlet extends HttpServlet {
    private DefaultProductService productService;

    public AddProductServlet(DefaultProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("name");
        String productPrice = req.getParameter("price");
        Product product = new Product();
        product.setPrice(Integer.parseInt(productPrice));
        product.setName(productName);
        int result = productService.save(product);
        if (result > 0) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.sendRedirect("/products");
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        String page = pageGenerator.getPage("addProduct.html");
        resp.getWriter().write(page);
    }
}
