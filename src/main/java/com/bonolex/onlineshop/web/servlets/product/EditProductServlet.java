package com.bonolex.onlineshop.web.servlets.product;

import com.bonolex.onlineshop.entity.Product;
import com.bonolex.onlineshop.service.DefaultProductService;
import com.bonolex.onlineshop.web.auth.TokenUtils;
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
 * Created by OBondar on 16.02.2019.
 */
public class EditProductServlet extends HttpServlet {
    private DefaultProductService productService;
    private List<String> tokens;

    public EditProductServlet(DefaultProductService productService) {
        this.productService = productService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        if (product != null) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("product", product);
            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("editProduct.html", pageVariables);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(page);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        product.setId(Integer.parseInt(request.getParameter("id")));
        product.setName(request.getParameter("name"));
        product.setPrice(Double.valueOf(request.getParameter("price")));
        int result = productService.update(product);
        if (result > 0) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/products");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        response.sendRedirect("/products");
    }
}
