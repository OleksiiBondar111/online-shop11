package com.bonolex.onlineshop.jdbc.entityrowmapper;

import com.bonolex.onlineshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by OBondar on 10.02.2019.
 */
public class ProductRowMapper {


    public Product mapRow(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("Id"));
        product.setName(resultSet.getString("product_name"));
        product.setPrice(Double.valueOf(resultSet.getString("product_price")));
        return product;
    }
}
