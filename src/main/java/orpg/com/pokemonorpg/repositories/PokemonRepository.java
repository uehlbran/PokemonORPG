package orpg.com.pokemonorpg.repositories;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;

import javax.persistence.QueryHint;
import java.util.Optional;

public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Long> {
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    Optional<Pokemon> findPokemonById(Long id);
}
