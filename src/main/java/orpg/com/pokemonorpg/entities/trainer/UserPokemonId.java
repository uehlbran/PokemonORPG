package orpg.com.pokemonorpg.entities.trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPokemonId implements Serializable {
    private Long userId;
    private Long pokemonId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPokemonId)) return false;
        UserPokemonId that = (UserPokemonId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(pokemonId, that.pokemonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, pokemonId);
    }
}
