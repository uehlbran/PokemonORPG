package orpg.com.pokemonorpg.entities.trainer;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class Trainer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainer_generator")
    @SequenceGenerator(name = "trainer_generator", sequenceName = "trainer_seq", initialValue = 100, allocationSize = 1)
    private Long id;
}
