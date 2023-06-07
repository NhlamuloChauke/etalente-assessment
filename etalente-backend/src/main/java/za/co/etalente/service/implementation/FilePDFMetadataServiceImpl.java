package za.co.etalente.service.implementation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@AllArgsConstructor
public class FilePDFMetadataServiceImpl implements FilePDFMetadataService {

    private final FilePDFMetadataRepo filePDFMetadataRepo;

    @Override
    public void savePdfFileDetails(String username, String csvFileInput, String pdfFileOutput,  String fileDestination) {
        FilePDFMetadata pdfFile = new FilePDFMetadata();
        pdfFile.setUsername(username);
        pdfFile.setCsvFileInput(csvFileInput);
        pdfFile.setPdfFileOutput(pdfFileOutput);
        pdfFile.setFileDestination(fileDestination);
    }

    @Override
    public String uploadToS3(byte[] pdfBytes) {
        S3Client s3Client = S3Client.create();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("your-bucket-name")
                .key("generated.pdf")
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
        String fileDestination = uploadToS3(pdfBytes);
        savePdfFileDetails(username,
                           csvContent,
                           pdfOutput,
                           fileDestination);
    }
}
