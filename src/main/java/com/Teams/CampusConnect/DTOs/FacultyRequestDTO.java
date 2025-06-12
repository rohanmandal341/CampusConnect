package com.Teams.CampusConnect.DTOs;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyRequestDTO {

    @NotBlank(message = "name shouldn't be blank")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "email shouldn't be blank")
    private String email;

    private String department;

    private String phone;

    private String address;


}
