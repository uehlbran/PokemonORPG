package orpg.com.pokemonorpg.entities.trainer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class Trainer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainer_generator")
    @SequenceGenerator(name = "trainer_generator", sequenceName = "trainer_seq", initialValue = 100, allocationSize = 1)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    private String icon;
}
