package orpg.com.pokemonorpg.repositories;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import orpg.com.pokemonorpg.entities.pokemon.Species;

import javax.persistence.QueryHint;
import java.util.Optional;

public interface SpeciesRepository extends CrudRepository<Species, Long> {
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    Optional<Species> findSpeciesById(Long id);
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    Optional<Species> findSpeciesByName(String name);
}
