package com.frontend.frontend.models;

import java.util.Date;

public class Company {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String idIdentification; // ID fiscal o de identificación
    private String economicActivity; // Actividad económica de la compañía
    private int createdBy;
    private int updatedBy;
    private Date createdAt;
    private Date updatedAt;

    // Constructor vacío
    public Company() {}

    // Constructor con parámetros
    public Company(int id, String name, String address, String phone, String email, String idIdentification,
                   String economicActivity, int createdBy, int updatedBy, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idIdentification = idIdentification;
        this.economicActivity = economicActivity;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
