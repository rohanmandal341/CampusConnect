package com.Teams.CampusConnect.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    private String username;
    private String password;
    private String role; // STUDENT / FACULTY / ADMIN
}
