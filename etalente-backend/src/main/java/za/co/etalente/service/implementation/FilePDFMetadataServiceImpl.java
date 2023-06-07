package za.co.etalente.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import za.co.etalente.model.FilePDFMetadata;
import za.co.etalente.repository.FilePDFMetadataRepo;
import za.co.etalente.service.FilePDFMetadataService;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class FilePDFMetadataServiceImpl implements FilePDFMetadataService {

    @Value("${etalent-bucket-test}")
    private String bucketName;

    final
    FilePDFMetadataRepo filePDFMetadataRepo;

    public FilePDFMetadataServiceImpl(FilePDFMetadataRepo filePDFMetadataRepo) {
        this.filePDFMetadataRepo = filePDFMetadataRepo;
    }

    @Override
    public void savePdfFileDetails(String username, String csvFileInput, String pdfFileOutput,  String fileDestination) {
        FilePDFMetadata pdfFile = new FilePDFMetadata();
        pdfFile.setUsername(username);
        pdfFile.setCsvFileInput(csvFileInput);
        pdfFile.setPdfFileOutput(pdfFileOutput);
        pdfFile.setFileDestination(fileDestination);
        pdfFile.setCreatedDate(new Date());
    }

    @Override
    public String uploadToS3(byte[] pdfBytes, String generatedPDF) {
        S3Client s3Client = S3Client.create();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(generatedPDF)
                .contentType("application/pdf")
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(pdfBytes));

        return "s3://" + putObjectRequest.bucket() + "/" + putObjectRequest.key();
    }

    @Override
    public byte[] generatePdf(String csvContent) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Generate PDF and obtain the byte array
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    @Transactional
    public void generatePdfAndUploadToS3(String csvContent, String username, String pdfOutput) {
        byte[] pdfBytes = generatePdf(csvContent);
        String fileDestination = uploadToS3(pdfBytes, pdfOutput);
        savePdfFileDetails(username,
                           csvContent,
                           pdfOutput,
                           fileDestination);
    }
}
