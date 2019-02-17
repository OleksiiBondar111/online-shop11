package com.bonolex.onlineshop.jdbc.entityrowmapper;

import com.bonolex.onlineshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by OBondar on 10.02.2019.
 */
public class UserRowMapper {
    public  User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("user_password"));
        user.setRole(resultSet.getString("user_role"));
        user.setSalt(resultSet.getString("salt"));
        return user;
    }
}
