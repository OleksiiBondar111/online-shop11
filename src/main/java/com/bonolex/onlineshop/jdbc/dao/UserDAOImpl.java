package com.bonolex.onlineshop.jdbc.dao;

import com.bonolex.onlineshop.entity.User;
import com.bonolex.onlineshop.jdbc.entityrowmapper.UserRowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OBondar on 14.02.2019.
 */
public class UserDAOImpl implements UserDAO {
    private DataSource dataSource;
    private static UserRowMapper userRowMapper = new UserRowMapper();

    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM users WHERE user_name = ?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String INSERT = "INSERT INTO users(user_name,user_role, password)VALUES(?,?,?)";
    private static final String UPDATE = "UPDATE users SET user_name=?, user_role=?, user_password=? WHERE id = ?";

    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int save(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.setString(3, user.getPassword());


            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public User findById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                User user = null;
                if (resultSet.next()) {
                    user = userRowMapper.mapRow(resultSet);
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(userRowMapper.mapRow(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public User findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {

            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                User user = null;
                if (resultSet.next()) {
                    user = userRowMapper.mapRow(resultSet);
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void setUserRowMapper(UserRowMapper userRowMapper) {
        UserDAOImpl.userRowMapper = userRowMapper;
    }

}
