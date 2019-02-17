package com.bonolex.onlineshop.service;

import com.bonolex.onlineshop.entity.User;
import com.bonolex.onlineshop.jdbc.dao.ProductDAO;
import com.bonolex.onlineshop.jdbc.dao.UserDAO;

import java.util.List;

/**
 * Created by OBondar on 14.02.2019.
 */
public class DefaultUserServiceImpl implements DefaultUserService {
    private UserDAO userDAO;


    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public int save(User user) {
        return userDAO.save(user);
    }

    @Override
    public int deleteById(int id) {
        return userDAO.delete(id);
    }

    @Override
    public int update(User user) {
        return userDAO.update(user);
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
