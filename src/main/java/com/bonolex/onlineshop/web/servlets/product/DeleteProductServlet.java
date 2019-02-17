package com.bonolex.onlineshop.web.servlets.product;

import com.bonolex.onlineshop.service.DefaultProductService;
import com.bonolex.onlineshop.web.auth.TokenUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by OBondar on 16.02.2019.
 */
public class DeleteProductServlet extends HttpServlet {
    private DefaultProductService productService;

    public DeleteProductServlet(DefaultProductService productService) {
        this.productService = productService;
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        int result = productService.deleteById(Integer.parseInt(request.getParameter("id")));
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
