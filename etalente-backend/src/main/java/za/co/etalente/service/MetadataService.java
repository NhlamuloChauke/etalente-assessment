package za.co.etalente.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;
import za.co.etalente.model.FileMetadata;

import java.io.IOException;
import java.util.List;

public interface MetadataService {
    public void upload(MultipartFile file) throws IOException;
    public S3Object download(int id);
    public List<FileMetadata> list();
}
