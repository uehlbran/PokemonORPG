package orpg.com.pokemonorpg.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import orpg.com.pokemonorpg.entities.trainer.UserPokemon;
import orpg.com.pokemonorpg.entities.trainer.UserPokemonId;

import java.util.Optional;

public interface UserPokemonRepository extends PagingAndSortingRepository<UserPokemon, UserPokemonId> {
    Optional<UserPokemon> findUserPokemonByUserIdAndPokemonId(long userId, Long pokemonId);
}
