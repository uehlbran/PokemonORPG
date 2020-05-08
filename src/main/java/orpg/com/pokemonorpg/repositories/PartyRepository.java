package orpg.com.pokemonorpg.repositories;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import orpg.com.pokemonorpg.entities.trainer.Party;

import javax.persistence.QueryHint;
import java.util.Optional;

public interface PartyRepository extends PagingAndSortingRepository<Party, Long> {
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    Optional<Party> findPartyById(Long id);
}
