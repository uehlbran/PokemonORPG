package orpg.com.pokemonorpg.services;

import org.springframework.stereotype.Service;
import orpg.com.pokemonorpg.entities.trainer.UserPokemon;
import orpg.com.pokemonorpg.repositories.UserPokemonRepository;

@Service
public class UserPokemonService {
    private final UserPokemonRepository repository;

    public UserPokemonService(UserPokemonRepository repository) {
        this.repository = repository;
    }

    public UserPokemon findUserPokemon(Long userId, Long pokemonId) {
        return repository.findUserPokemonByUserIdAndPokemonId(userId, pokemonId)
                .orElseThrow(() -> new RuntimeException("Unable to find user pokemon"));
    }

    public UserPokemon save(UserPokemon userPokemon) {
        return repository.save(userPokemon);
    }
}
