package com.bonolex.onlineshop.jdbc.dao;

import com.bonolex.onlineshop.entity.Product;

import java.util.List;

/**
 * Created by OBondar on 10.02.2019.
 */
public interface ProductDAO {
    int save(Product product);

    int update(Product product);

    int delete(int id);

    Product fndById(int id);

    List<Product> findAll();
}
