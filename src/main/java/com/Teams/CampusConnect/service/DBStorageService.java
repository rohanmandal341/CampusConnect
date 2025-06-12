package com.Teams.CampusConnect.service;



import com.Teams.CampusConnect.exception.ResourceNotFoundException;
import com.Teams.CampusConnect.model.StudentDoc;
import com.Teams.CampusConnect.repository.StudentDocRepository;
import com.Teams.CampusConnect.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DBStorageService {

    private final StudentDocRepository studentDocRepository;
    private final EmailService emailService;

    public StudentDoc saveToDB(MultipartFile file) throws IOException{
        StudentDoc doc = new StudentDoc();
        doc.setFileName(file.getOriginalFilename());
        doc.setFileType(file.getContentType());
        doc.setData(file.getBytes());
        String subject = "New Assignment Uploaded";
        String body = "Assignment \"" + file.getOriginalFilename() + "\" uploaded successfully.";
        emailService.sendEmail("rohanmandal7900@gmail.com", subject, body);

        return studentDocRepository.save(doc);
    }

    public StudentDoc getDoc(Long id){
        return studentDocRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("file not found"));
    }
}
