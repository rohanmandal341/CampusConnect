package com.Teams.CampusConnect.DTOs;
import com.Teams.CampusConnect.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private Long id;

    private  String name;

    private String email;

    private String department;

    private LocalDate dob;

    private  String phone;

    private String address;


}
