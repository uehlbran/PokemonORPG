package orpg.com.pokemonorpg.entities.trainer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import orpg.com.pokemonorpg.entities.Gender;
import orpg.com.pokemonorpg.entities.Image;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Getter @Setter
public class Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainer_generator")
    @SequenceGenerator(name = "trainer_generator", sequenceName = "trainer_seq", initialValue = 100, allocationSize = 1)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "icon_id")
    private Image icon;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private LocalDate dob;
}
