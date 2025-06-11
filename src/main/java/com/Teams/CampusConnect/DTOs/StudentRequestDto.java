package com.Teams.CampusConnect.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {

    @NotBlank(message = "name is required")
    private  String name;

    @Email(message = "email should be valid")
    @NotBlank(message = "email shouldn't be empty")
    private String email;

    private String department;

    private LocalDate dob;

    private  String phone;

    private String address;


}
