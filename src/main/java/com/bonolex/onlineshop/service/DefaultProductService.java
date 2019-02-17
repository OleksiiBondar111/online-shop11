package com.bonolex.onlineshop.service;

import com.bonolex.onlineshop.entity.Product;

import java.util.List;

/**
 * Created by OBondar on 10.02.2019.
 */
public interface DefaultProductService {
    List<Product> findAll();

    Product findById(int id);

    int save(Product product);

    int deleteById(int id);

    int update(Product product);


}
