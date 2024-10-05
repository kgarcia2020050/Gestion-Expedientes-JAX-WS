package com.jaxws.dtos;

import com.jaxws.models.Company;
import com.jaxws.models.Department;
import com.jaxws.models.User;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "RESPONSE_USER")
public class Response {
    private String message;
    private String token;

    private String role;
    private int status;
    private List<User> users;
    private List<Company> companies;
    private List<Department> departments;

    private List<String> errors;

    private boolean success;

    @XmlElement(name = "MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement(name = "STATUS")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @XmlElement(name = "ERRORS")
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @XmlElement(name = "SUCCESS")
    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    @XmlElement(name = "TOKEN")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @XmlElement(name = "ROLE")
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @XmlElement(name = "USERS")

    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

    @XmlElement(name = "COMPANIES")

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    @XmlElement(name = "DEPARTMENTS")

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
