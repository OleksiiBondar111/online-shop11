package com.bonolex.onlineshop.jdbc.dao;

import com.bonolex.onlineshop.entity.User;

import java.util.List;

/**
 * Created by OBondar on 10.02.2019.
 */
public interface UserDAO {
    int save(User user);

    int update(User user);

    int delete(int id);

    User findById(int id);

    User findByName(String name);

    List<User> findAll();
}
