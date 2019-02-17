package com.bonolex.onlineshop.service;

import com.bonolex.onlineshop.entity.User;

import java.util.List;

/**
 * Created by OBondar on 10.02.2019.
 */
public interface DefaultUserService {
    List<User> findAll();

    User findById(int id);

    int save(User user);

    int deleteById(int id);

    int update(User user);

    User findByName(String name);


}
