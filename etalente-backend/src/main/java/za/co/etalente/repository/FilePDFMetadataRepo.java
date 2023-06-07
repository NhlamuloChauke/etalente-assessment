package za.co.etalente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.etalente.model.FilePDFMetadata;

@Repository
public interface FilePDFMetadataRepo extends CrudRepository<FilePDFMetadata, Long> {
}
