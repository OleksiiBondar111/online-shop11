package com.bonolex.onlineshop.jdbc.dao;

import com.bonolex.onlineshop.entity.Product;
import com.bonolex.onlineshop.jdbc.entityrowmapper.ProductRowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OBondar on 10.02.2019.
 */
public class ProductDAOImpl implements ProductDAO {
    private DataSource dataSource;
    private static ProductRowMapper productRowMapper = new ProductRowMapper();

    private static final String FIND_ALL = "SELECT * FROM products";
    private static final String FIND_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM products WHERE id = ?";
    private static final String INSERT = "INSERT INTO products(product_name,product_price)VALUES(?,?)";
    private static final String UPDATE = "UPDATE products SET product_name=?, product_price=? WHERE id = ?";

    public ProductDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int save(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public int update(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int delete(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Product fndById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Product product = null;
                if (resultSet.next()) {
                    product = productRowMapper.mapRow(resultSet);
                }
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(productRowMapper.mapRow(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static void setProductRowMapper(ProductRowMapper productRowMapper) {
        ProductDAOImpl.productRowMapper = productRowMapper;
    }
}
