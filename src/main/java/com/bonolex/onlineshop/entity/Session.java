package com.bonolex.onlineshop.entity;

import com.bonolex.onlineshop.entity.User;

import java.time.LocalDateTime;

/**
 * Created by OBondar on 16.02.2019.
 */
public class Session {
    private String token;
    private User user;
    private LocalDateTime expiredDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }
}
