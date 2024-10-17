package com.jaxws.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.sql.Date;

@XmlRootElement(name = "COMPANY")
public class Company implements Serializable {

    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String idIdentification;
    private String economicActivity;
    private Integer createdBy;
    private Integer updatedBy;
    private Date createdAt;
    private Date updatedAt;

    private boolean active;

    public long getDate() {
        java.util.Date actualDate = new Date(System.currentTimeMillis());
        return actualDate.getTime();
    }

    public Company(int id, String name, String address, String phone, String email, String economicActivity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.economicActivity = economicActivity;
        this.createdAt = new Date(getDate());
        this.updatedAt = new Date(getDate());

    }


    public Company(String name, String address, String phone, String email, String idIdentification, String economicActivity) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idIdentification = idIdentification;
        this.economicActivity = economicActivity;
        this.createdAt = new Date(getDate());
        this.updatedAt = new Date(getDate());

    }

    public Company() {

    }

    @XmlElement(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "ID_IDENTIFICATION")
    public String getIdIdentification() {
        return idIdentification;
    }

    public void setIdIdentification(String idIdentification) {
        this.idIdentification = idIdentification;
    }

    @XmlElement(name = "ECONOMIC_ACTIVITY")
    public String getEconomicActivity() {
        return economicActivity;
    }

    public void setEconomicActivity(String economicActivity) {
        this.economicActivity = economicActivity;
    }

    @XmlElement(name = "CREATED_BY")
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @XmlElement(name = "UPDATED_BY")
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @XmlElement(name = "CREATED_AT")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @XmlElement(name = "UPDATED_AT")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlElement(name = "IS_ACTIVE")

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
