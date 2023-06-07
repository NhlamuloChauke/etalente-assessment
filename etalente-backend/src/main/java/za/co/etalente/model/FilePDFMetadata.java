package za.co.etalente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "FILE_METADATA)")
@Data
@AllArgsConstructor
public class FilePDFMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String csvFileInput;

    @Column(nullable = false)
    private String pdfFileOutput;

    @Column(nullable = false)
    private String fileDestination;
}
