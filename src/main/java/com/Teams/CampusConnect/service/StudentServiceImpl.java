package com.Teams.CampusConnect.service;


import com.Teams.CampusConnect.DTOs.StudentRequestDto;
import com.Teams.CampusConnect.DTOs.StudentResponseDto;
import com.Teams.CampusConnect.exception.ResourceNotFoundException;
import com.Teams.CampusConnect.model.Student;
import com.Teams.CampusConnect.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final EmailService emailService;

    private StudentResponseDto toResponseDTO(Student student){
        StudentResponseDto dto =  new StudentResponseDto();
        BeanUtils.copyProperties(student,dto);
        return dto;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto dto){

        if(studentRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("email already exists");
        }
        log.info("saving the data in DB");
        Student student = Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .dob(dto.getDob())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .build();
        Student saved = studentRepository.save(student);
        log.info("successfully saved into DB : {}",student);

        String subject = "Welcome to CampusConnect!";
        String body = "Hi " + saved.getName() + ",\n\nYour registration is successful.\n\nRegards!\nCampus-connect.";

        emailService.sendEmail(saved.getEmail(),subject,body);
        return toResponseDTO(saved);
    }

    @Override
    public Page<StudentResponseDto> getAllStudents(int page, int size, String sortBy){
        Pageable pageable =  PageRequest.of(page,size, Sort.by(sortBy).ascending());
        return studentRepository.findAll(pageable)
                .map(this::toResponseDTO);
    }

    @Override
    public StudentResponseDto getStudentById(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return toResponseDTO(student);
    }

    @Override
    public StudentResponseDto updateStudent(StudentRequestDto studentRequestDto,Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not found"));


        BeanUtils.copyProperties(studentRequestDto,student,"id");
        Student update = studentRepository.save(student);
        return toResponseDTO(update);
    }

    @Override
    public void deleteStudent(Long id){
        if(!studentRepository.existsById(id)){
            throw new ResourceNotFoundException("Id not found");
        }
        studentRepository.deleteById(id);
    }

}
