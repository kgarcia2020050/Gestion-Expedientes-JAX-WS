package com.jaxws.models;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.sql.Date;

@XmlRootElement(name = "DEPARTMENT")
public class Department implements Serializable {

    private int id;
    private int companyId;
    private String name;
    private String description;
    private Integer createdBy;
    private Integer updatedBy;
    private Date createdAt;
    private Date updatedAt;


    public long getDate() {
        java.util.Date actualDate = new Date(System.currentTimeMillis());
        return actualDate.getTime();
    }


    public Department() {

    }

    public Department(int companyId, String name, String description) {

        this.companyId = companyId;
        this.name = name;
        this.description = description;
        this.createdAt = new Date(getDate());
        this.updatedAt = new Date(getDate());

    }


    @XmlElement(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "COMPANY_ID")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
