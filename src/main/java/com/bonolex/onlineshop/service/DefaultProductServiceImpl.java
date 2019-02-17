package com.bonolex.onlineshop.service;

import com.bonolex.onlineshop.entity.Product;
import com.bonolex.onlineshop.jdbc.dao.ProductDAO;

import java.util.List;

/**
 * Created by OBondar on 10.02.2019.
 */
public class DefaultProductServiceImpl implements DefaultProductService {

    private ProductDAO productDAO;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.fndById(id);
    }

    @Override
    public int save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public int deleteById(int id) {
        return productDAO.delete(id);
    }

    @Override
    public int update(Product product) {
        return productDAO.update(product);
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
