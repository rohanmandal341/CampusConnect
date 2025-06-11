package com.Teams.CampusConnect.service;

import com.Teams.CampusConnect.DTOs.StudentRequestDto;
import com.Teams.CampusConnect.DTOs.StudentResponseDto;
import org.springframework.data.domain.Page;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto dto);
    StudentResponseDto getStudentById(Long id);
    Page<StudentResponseDto> getAllStudents(int page, int size, String sortBy);
    StudentResponseDto updateStudent(StudentRequestDto dto,Long id);
    void deleteStudent(Long id);

}
