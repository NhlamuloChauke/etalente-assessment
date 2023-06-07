package za.co.etalente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import za.co.etalente.model.FilePDFMetadata;
import za.co.etalente.service.FilePDFMetadataService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/pdf")
public class PdfController {


    @Autowired
    private FilePDFMetadataService pdfService;

    @PostMapping(value = "/generatePDFAndUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void generatePdfAndUploadToS3(@RequestParam("username") String username,
                                         @RequestParam("pdf-file-output") String pdfFileName,
                                         @RequestPart("csvFile") MultipartFile csvFile) {
        try {
            String csvContent = new String(csvFile.getBytes(), StandardCharsets.UTF_8);
            pdfService.generatePdfAndUploadToS3(csvContent, username, pdfFileName+".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
