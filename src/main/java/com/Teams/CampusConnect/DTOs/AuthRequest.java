package com.Teams.CampusConnect.DTOs;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}