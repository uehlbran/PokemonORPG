package orpg.com.pokemonorpg.repositories;

import org.springframework.data.repository.CrudRepository;
import orpg.com.pokemonorpg.entities.pokemon.Species;

import java.util.Optional;

public interface SpeciesRepository extends CrudRepository<Species, Long> {
    Optional<Species> findSpeciesById(Long id);
}
