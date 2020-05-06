package orpg.com.pokemonorpg.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;

import java.util.Optional;

public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Long> {
    Optional<Pokemon> findPokemonById(Long id);
}
