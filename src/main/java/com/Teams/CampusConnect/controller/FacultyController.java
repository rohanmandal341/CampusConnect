package com.Teams.CampusConnect.controller;

import com.Teams.CampusConnect.DTOs.FacultyRequestDTO;
import com.Teams.CampusConnect.DTOs.FacultyResponseDTO;
import com.Teams.CampusConnect.model.Faculty;
import com.Teams.CampusConnect.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping
    public ResponseEntity<FacultyResponseDTO> createFaculty(@Valid @RequestBody FacultyRequestDTO dto) {
        FacultyResponseDTO savedDto = facultyService.createFaculty(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto); // ✅ Return DTO, not Entity
    }
    @GetMapping("/{id}")
    public FacultyResponseDTO getById(@PathVariable Long id){
       return facultyService.getFaculty(id);

    }
    @GetMapping
    public Page<FacultyResponseDTO> getAllFaculty(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return facultyService.getAll(page, size);
    }

    @PutMapping("/{id}")
    public FacultyResponseDTO update(
            @Valid @RequestBody FacultyRequestDTO dto,
            @PathVariable Long id
    ) {
        return facultyService.updateFaculty(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build(); // ✅ Better REST practice
    }
}
