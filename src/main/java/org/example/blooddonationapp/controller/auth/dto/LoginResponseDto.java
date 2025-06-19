package org.example.blooddonationapp.controller.auth.dto;

import org.example.blooddonationapp.commontypes.UserRole;

public class LoginResponseDto {
    private String token;
    private UserRole role;

    public LoginResponseDto(String token, UserRole role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
