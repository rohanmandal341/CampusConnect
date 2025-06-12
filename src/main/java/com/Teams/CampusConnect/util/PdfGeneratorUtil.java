package com.Teams.CampusConnect.util;

import com.Teams.CampusConnect.model.Marksheet;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfGeneratorUtil {

    public static String generatePdf(Marksheet marksheet) throws IOException {
        String basePath = System.getProperty("user.dir") + "/uploads/marksheet";

        // Ensure the folder exists
        java.io.File folder = new java.io.File(basePath);
        if (!folder.exists()) {
            folder.mkdirs(); // create /uploads/marksheet if missing
        }

        String filePath = basePath + "/marksheet_" + marksheet.getStudentName() + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 18);
        Paragraph title = new Paragraph("Student Marksheet", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Name: " + marksheet.getStudentName()));
        document.add(new Paragraph("Roll No: " + marksheet.getRollNumber()));
        document.add(new Paragraph("Department: " + marksheet.getDepartment()));
        document.add(new Paragraph(" "));

        for (var subject : marksheet.getSubjectMarks().entrySet()) {
            document.add(new Paragraph(subject.getKey() + ": " + subject.getValue()));
        }

        document.add(new Paragraph("Total: " + marksheet.getTotal()));
        document.add(new Paragraph("Grade: " + marksheet.getGrade()));
        document.add(new Paragraph("\nSignature: _______________"));

        document.close();
        return filePath;
    }





}
