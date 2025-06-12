package com.Teams.CampusConnect.service;

import com.Teams.CampusConnect.DTOs.FacultyRequestDTO;
import com.Teams.CampusConnect.DTOs.FacultyResponseDTO;
import org.springframework.data.domain.Page;

public interface FacultyService {
    FacultyResponseDTO createFaculty(FacultyRequestDTO dto);
    Page<FacultyResponseDTO> getAll(int page, int size);
    FacultyResponseDTO getFaculty(Long id);
    FacultyResponseDTO updateFaculty(FacultyRequestDTO dto, Long id);
    void deleteFaculty(Long id);


}
