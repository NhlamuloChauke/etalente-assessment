package za.co.etalente.service;

public interface FilePDFMetadataService {
    void savePdfFileDetails(String username, String csvFileInput, String pdfFileOutput,  String fileDestination);
    String uploadToS3(byte[] pdfBytes, String generatedPDF);
    byte[] generatePdf(String csvContent);
    void generatePdfAndUploadToS3(String csvContent, String username, String pdfOutput);
}
