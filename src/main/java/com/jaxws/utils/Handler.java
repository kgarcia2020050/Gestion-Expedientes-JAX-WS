package com.jaxws.utils;

import com.jaxws.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Handler implements SOAPHandler<SOAPMessageContext> {

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (!outbound) {
            Map<String, List<String>> headers = (Map<String, List<String>>) context.get(SOAPMessageContext.HTTP_REQUEST_HEADERS);
            List<String> authHeaders = headers.get("Authorization");

            String token = null;
            if (authHeaders != null && !authHeaders.isEmpty()) {
                token = authHeaders.get(0);
                if (token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }
            }

            if (token != null) {
                Claims claims = JwtUtil.validateToken(token);
                String id = claims.getId();
                setUserId(Integer.parseInt(id));
            } else {
                throw new SecurityException("Missing or invalid Authorization header");
            }
        }

        return true;
    }


    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }
}
