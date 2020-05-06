package orpg.com.pokemonorpg.services;

import org.springframework.stereotype.Service;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;
import orpg.com.pokemonorpg.repositories.PokemonRepository;

@Service
public class PokemonService {
    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public Pokemon findPokemonById(Long id) {
        return repository.findPokemonById(id).orElseThrow(() -> new RuntimeException("Pokemon not found"));
    }

    public Pokemon save(Pokemon pokemon) {
        return repository.save(pokemon);
    }
}
