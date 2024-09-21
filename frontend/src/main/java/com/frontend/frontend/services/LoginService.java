package com.frontend.frontend.services;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.frontend.frontend.wsdl.LOGIN;
import org.springframework.ws.soap.client.core.SoapActionCallback;
public class LoginService extends WebServiceGatewaySupport {
    public Object login(String email, String password) {

        LOGIN request = new LOGIN();
        request.setEMAIL(email);
        request.setPASSWORD(password);

        Object response = (Object) getWebServiceTemplate()
                .marshalSendAndReceive("http://DESKTOP-5QHEAJA:8080/jaxws-1.0-SNAPSHOT/AuthController?wsdl", request, new SoapActionCallback("LOGIN"));

        return response;
    }
}

