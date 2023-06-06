package za.co.etalente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.etalente.model.FileMetadata;

@Repository
public interface FileMetadataRepo extends CrudRepository<FileMetadata, Long> {
}
