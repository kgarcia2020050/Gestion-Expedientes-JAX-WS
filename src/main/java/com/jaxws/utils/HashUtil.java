package com.jaxws.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    public boolean verifyPassword(String password, String hash) {
        try {
            return hashPassword(password).equals(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;

    }


    public String decryptPassword(String password) {
        return new String(Base64.getDecoder().decode(password));
    }
}
