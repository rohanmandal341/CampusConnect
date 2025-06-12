package com.Teams.CampusConnect.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Marksheet {

    private String studentName;
    private String rollNumber;
    private String department;
    private Map<String, Integer> subjectMarks;
    private int total;
    private String grade;
}
