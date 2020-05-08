package orpg.com.pokemonorpg.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileLocation;
    private String fileExtension;
    private String imageName;

    public Image(String fileLocation, String fileName, String fileExtension, String imageName) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return fileLocation + fileName + fileExtension;
    }
}
