package com.Teams.CampusConnect.controller;

import com.Teams.CampusConnect.model.StudentDoc;
import com.Teams.CampusConnect.service.DBStorageService;
import com.Teams.CampusConnect.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final DBStorageService dbStorageService;
    private final FileStorageService fileStorageService;

    @PostMapping("/upload/db")
    public ResponseEntity<String> uplaodDb(MultipartFile file) throws IOException {
        log.info("got request to post file");
         dbStorageService.saveToDB(file);
         return  ResponseEntity.ok("saved to DB");

    }
    @PostMapping("/upload/fs")
    public ResponseEntity<String> uplaodFS(MultipartFile file) throws IOException {
        log.info("got request to post file");
        fileStorageService.saveToFileSystem(file);
        log.info("sent request to post file");
        return  ResponseEntity.ok("saved to File System");

    }

    @GetMapping("/download/db/{id}")
    public ResponseEntity<byte[]> downloadDb(@PathVariable Long id){
        StudentDoc doc = dbStorageService.getDoc(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment: filename="+ doc.getFileName())
                .contentType(MediaType.parseMediaType(doc.getFileType()))
                .body(doc.getData());
    }

    @GetMapping("/download/fs/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) throws IOException{
        byte[] file = fileStorageService.getFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }



}
