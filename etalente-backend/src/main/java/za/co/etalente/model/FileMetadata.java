package za.co.etalente.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "FILE_METADATA)")
@Data
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "VERSION")
    private String version;

    public FileMetadata(Long id, String fileName, String filePath, String version) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.version = version;
    }
}
