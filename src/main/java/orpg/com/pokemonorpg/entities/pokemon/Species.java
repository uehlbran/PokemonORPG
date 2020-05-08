package orpg.com.pokemonorpg.entities.pokemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import orpg.com.pokemonorpg.entities.Image;
import orpg.com.pokemonorpg.services.SpeciesService;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long pokedexNumber;
    @NaturalId
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

    public static class Factory {

        public static Species getSpecies(String speciesName, SpeciesService service) {
            return service.findSpeciesByName(speciesName);
        }
        public static Species createSpecies() {
            return null;
        }
        private static Species createNormalSpecies(String speciesName) {
            return null;
        }
    }
}
