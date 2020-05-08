package orpg.com.pokemonorpg.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;
import orpg.com.pokemonorpg.repositories.PokemonRepository;

@Service
public class PokemonService {
    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Pokemon findPokemonById(Long id) {
        return repository.findPokemonById(id).orElseThrow(() -> new RuntimeException("Pokemon not found"));
    }
    @Transactional
    public Pokemon save(Pokemon pokemon) {
        return repository.save(pokemon);
    }
}
