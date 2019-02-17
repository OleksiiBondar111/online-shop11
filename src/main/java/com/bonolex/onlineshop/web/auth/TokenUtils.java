package com.bonolex.onlineshop.web.auth;

import com.bonolex.onlineshop.service.DefaultSecurityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by OBondar on 10.02.2019.
 */
public class TokenUtils {

    public static Cookie logout(HttpServletRequest request, DefaultSecurityService defaultSecurityService) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    defaultSecurityService.getSession("user-token");
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        }
        return null;
    }

}
