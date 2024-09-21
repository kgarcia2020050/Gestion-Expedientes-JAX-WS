package com.frontend.frontend.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class AuthServiceImpl {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.frontend.frontend.wsdl");
        return marshaller;
    }

    @Bean
    public LoginService countryClient(Jaxb2Marshaller marshaller) {
        LoginService client = new LoginService();
        client.setDefaultUri("http://DESKTOP-5QHEAJA:8080/jaxws-1.0-SNAPSHOT/AuthController?wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
