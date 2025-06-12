package com.Teams.CampusConnect.service;

import com.Teams.CampusConnect.DTOs.FacultyRequestDTO;
import com.Teams.CampusConnect.DTOs.FacultyResponseDTO;
import com.Teams.CampusConnect.exception.ResourceNotFoundException;
import com.Teams.CampusConnect.model.Faculty;
import com.Teams.CampusConnect.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    private FacultyResponseDTO convert(Faculty faculty){
        FacultyResponseDTO dto = new FacultyResponseDTO();
        BeanUtils.copyProperties(faculty, dto);
        return dto;
    }

    @Override
    public FacultyResponseDTO createFaculty(FacultyRequestDTO dto){
        if(facultyRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("email already exists");
        }
        Faculty faculty = Faculty.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .department(dto.getDepartment())
                .address(dto.getAddress())
                .build();
        Faculty saved = facultyRepository.save(faculty);
        return convert(saved);
    }

    @Override
    public Page<FacultyResponseDTO> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page,size);

        return facultyRepository.findAll(pageable).map(this::convert);
    }

    @Override
    public FacultyResponseDTO getFaculty(Long id){
       Faculty faculty = facultyRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("faculty with this id is not available"));

       return convert(faculty);
    }

    @Override
    public FacultyResponseDTO updateFaculty(FacultyRequestDTO dto, Long id){
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id not found"));
        BeanUtils.copyProperties(dto, faculty,"id");
        Faculty saved = facultyRepository.save(faculty);
        return convert(saved);
    }

    @Override
    public void deleteFaculty(Long id){
        if(!facultyRepository.existsById(id)){
            throw new ResourceNotFoundException("id not found");
        }
        facultyRepository.deleteById(id);
    }
}
