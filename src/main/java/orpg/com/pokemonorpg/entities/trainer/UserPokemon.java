package orpg.com.pokemonorpg.entities.trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPokemon {
    @EmbeddedId
    private UserPokemonId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("pokemonId")
    private Pokemon pokemon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPokemon)) return false;
        UserPokemon that = (UserPokemon) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(pokemon, that.pokemon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, pokemon);
    }
}
