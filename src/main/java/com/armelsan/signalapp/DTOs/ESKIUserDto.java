package com.armelsan.signalapp.DTOs;

import com.armelsan.signalapp.model.Department;

public class ESKIUserDto {

    private Long id;
    private String username;
    private String email;
    private Department department;
    private String role;

    private boolean requiresPasswordChange;

    public ESKIUserDto(Long id, String username, String email, Department department, String role, boolean requiresPasswordChange) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.department = department;
        this.role = role;
        this.requiresPasswordChange = requiresPasswordChange;
    }

    public ESKIUserDto() {

    }

    public boolean isRequiresPasswordChange() {
        return requiresPasswordChange;
    }

    public void setRequiresPasswordChange(boolean requiresPasswordChange) {
        this.requiresPasswordChange = requiresPasswordChange;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}