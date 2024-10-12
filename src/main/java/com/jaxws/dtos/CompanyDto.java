package com.jaxws.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement
public class CompanyDto extends Request implements Serializable {


    private String name;
    private String address;
    private String phone;
    private String email;
    private String idIdentification;
    private String economicActivity;

    public CompanyDto() {
    }

    public CompanyDto(String name, String address, String phone, String email, String idIdentification, String economicActivity) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idIdentification = idIdentification;
        this.economicActivity = economicActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdIdentification() {
        return idIdentification;
    }

    public void setIdIdentification(String idIdentification) {
        this.idIdentification = idIdentification;
    }

    public String getEconomicActivity() {
        return economicActivity;
    }

    public void setEconomicActivity(String economicActivity) {
        this.economicActivity = economicActivity;
    }
}
