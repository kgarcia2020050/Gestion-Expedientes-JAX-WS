package com.jaxws.dtos;

import java.sql.Date;

public class DepartmentDto {

    private int companyId;
    private String name;
    private String description;


    public DepartmentDto(int companyId, String name, String description) {
        this.companyId = companyId;
        this.name = name;
        this.description = description;
    }

    public DepartmentDto() {

    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
