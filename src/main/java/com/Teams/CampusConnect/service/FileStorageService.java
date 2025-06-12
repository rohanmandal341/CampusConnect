package com.Teams.CampusConnect.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
    private final EmailService emailService;

    public String saveToFileSystem(MultipartFile file) throws IOException {
        File dir = new File(UPLOAD_DIR);
        if(!dir.exists()) dir.mkdirs();

        String path = UPLOAD_DIR + file.getOriginalFilename();

        file.transferTo(new File(path));
        String subject = "New Assignment Uploaded";
        String body = "Assignment \"" + file.getOriginalFilename() + "\" uploaded successfully.";
        emailService.sendEmail("rohanmandal7900@gmail.com", subject, body);
        return path;
    }

    public byte[] getFile(String filename)throws IOException{

        return Files.readAllBytes(Paths.get(UPLOAD_DIR+filename));

    }
}

