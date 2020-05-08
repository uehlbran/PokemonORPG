package orpg.com.pokemonorpg.entities.pokemon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import orpg.com.pokemonorpg.entities.Gender;
import orpg.com.pokemonorpg.entities.trainer.Party;
import orpg.com.pokemonorpg.entities.trainer.User;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "species_id")
    private Species species;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    public static class Factory {
        public static Pokemon createPokemon(User user, Species species, Gender gender) {
            Pokemon pokemon = new Pokemon();
            pokemon.setSpecies(species);
            pokemon.setUser(user);
            pokemon.setGender(gender);
            return pokemon;
        }
    }
}
