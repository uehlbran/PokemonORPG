package orpg.com.pokemonorpg.entities.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import orpg.com.pokemonorpg.entities.Image;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long pokedexNumber;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private ElementType type1;
    @Enumerated(value = EnumType.STRING)
    private ElementType type2;
    private double captureRate;
    @Transient //TODO: Create system for determining pokemon gender ratio
    private double genderRate;
    private int generation;
    //TODO: Add evolution lines
    @OneToOne
    @JoinColumn(name = "icon_id")
    private Image icon;
    @Enumerated(value = EnumType.STRING)
    private PokemonType pokemonType;
}
