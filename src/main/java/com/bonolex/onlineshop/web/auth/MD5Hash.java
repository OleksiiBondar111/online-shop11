package com.bonolex.onlineshop.web.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by OBondar on 16.02.2019.
 */
public class MD5Hash {
    public static void main(String[] args) {

        String password = "123";
        String salt = "lol";

        System.out.println("MD5 in hex: " + getMD5HashValue(password + salt));

    }


    public static String getMD5HashValue(String input) {
        String md5 = null;

        if (null == input) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }
}

