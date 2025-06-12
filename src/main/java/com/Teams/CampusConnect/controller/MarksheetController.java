package com.Teams.CampusConnect.controller;

import com.Teams.CampusConnect.model.Marksheet;
import com.Teams.CampusConnect.util.PdfGeneratorUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/marksheets")
public class MarksheetController {

    @PostMapping("/generate")
    public ResponseEntity<?> generateMarksheet(@RequestBody Marksheet marksheet) {
        try {
            String file = PdfGeneratorUtil.generatePdf(marksheet);
            return ResponseEntity.ok("Marksheet PDF generated: " + file);
        } catch (IOException ex) {
            return ResponseEntity.status(500).body("Error generating PDF");
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadMarksheet(@PathVariable("filename") String filename) throws IOException {
        Path path = Paths.get(System.getProperty("user.dir") + "/uploads/marksheet/" + filename);

        if (!Files.exists(path)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_PDF)
                .body(fileBytes);
    }
}
