package com.jaxws.dtos;

import com.jaxws.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "REQUEST")
public class Request {
    private String authToken;


    public Request() {
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    public boolean validateToken() {
        if (this.authToken != null) {
            JwtUtil.validateToken(authToken);
            return true;
        } else {
            throw new SecurityException("Missing or invalid Authorization header");
        }
    }


    public int getIdToken(){
        if (this.authToken != null) {
            Claims claims = JwtUtil.validateToken(authToken);
            return Integer.parseInt(claims.getId());
        } else {
            throw new SecurityException("Missing or invalid Authorization header");
        }
    }

}
