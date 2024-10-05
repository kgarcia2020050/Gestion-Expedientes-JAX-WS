package com.jaxws.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.sql.Date;

@XmlRootElement(name = "USER")
public class User implements Serializable {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private String role;
    private Date lastLogin;
    private Integer createdBy;
    private Integer updatedBy;
    private Date createdAt;
    private Date updatedAt;
    private boolean active;

    public long getDate() {
        java.util.Date actualDate = new Date(System.currentTimeMillis());
        return actualDate.getTime();
    }

    public User(String email, String firstName, String password, String lastName, int createdBy, int updatedBy, boolean active, String role) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.active = active;
        this.role = role;
        this.lastLogin = new Date(getDate());
        this.createdAt = new Date(getDate());
        this.updatedAt = new Date(getDate());
    }


    public User(String email, String firstName, String password, String lastName, int updatedBy, boolean active, String role) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.updatedBy = updatedBy;
        this.active = active;
        this.role = role;
        this.updatedAt = new Date(getDate());
    }


    public User(String email, String firstName, String password, String lastName, boolean active, String role) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.active = active;
        this.updatedAt = new Date(getDate());
    }



    public User(){}


    @XmlElement(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "EMAIL")

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "PASSWORD")

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement(name = "FIRST_NAME")

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "LAST_NAME")

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "LAST_LOGIN")

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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
