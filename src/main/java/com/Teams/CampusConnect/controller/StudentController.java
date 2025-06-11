package com.Teams.CampusConnect.controller;

import com.Teams.CampusConnect.DTOs.StudentRequestDto;
import com.Teams.CampusConnect.DTOs.StudentResponseDto;
import com.Teams.CampusConnect.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public StudentResponseDto addStudent(@Valid @RequestBody StudentRequestDto dto) {
        return studentService.createStudent(dto);
    }

    @GetMapping
    public Page<StudentResponseDto> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return studentService.getAllStudents(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDto update(@Valid @RequestBody StudentRequestDto studentRequestDto,
                                     @PathVariable Long id) {
        return studentService.updateStudent(studentRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
