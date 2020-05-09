package orpg.com.pokemonorpg.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import orpg.com.pokemonorpg.entities.trainer.User;

import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @EntityGraph(attributePaths = {"userDetails.roles"})
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    Optional<User> findUserByUserDetailsUsername(String username);
    @EntityGraph(attributePaths = {"userDetails.roles"})
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    Optional<User> findUserById(Long id);
}
