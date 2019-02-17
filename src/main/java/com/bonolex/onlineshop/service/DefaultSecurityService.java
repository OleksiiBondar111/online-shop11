package com.bonolex.onlineshop.service;

import com.bonolex.onlineshop.entity.Session;
import com.bonolex.onlineshop.entity.User;
import com.bonolex.onlineshop.web.auth.MD5Hash;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by OBondar on 16.02.2019.
 */
public class DefaultSecurityService {
    private DefaultUserService defaultUserService;
    private List<Session> sessionList = Collections.synchronizedList(new ArrayList<>());

    public DefaultSecurityService(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public Session login(String name, String password) {
        User user = defaultUserService.findByName(name);
        if(user!=null) {
            String generatedPassword = MD5Hash.getMD5HashValue(password + user.getSalt());
            if (user.getPassword().equals(generatedPassword)) {
                Session session = new Session();
                String token = UUID.randomUUID().toString();
                session.setToken(token);
                session.setUser(user);
                session.setExpiredDate(LocalDateTime.now().plusHours(2));
                sessionList.add(session);
                return session;
            }
        }
        return null;
    }


    public Session getSession(String token) {
        for (Session session : sessionList) {
            if (session.getToken().equals(token)) {
                if (isSessionExpired(session)) {
                    sessionList.remove(session);
                    return null;
                }
                return session;
            }
        }
        return null;
    }

    private boolean isSessionExpired(Session session) {
        return session.getExpiredDate().isBefore(LocalDateTime.now());
    }

}