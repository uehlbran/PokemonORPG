package orpg.com.pokemonorpg.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import orpg.com.pokemonorpg.entities.trainer.User;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(Long id);
}
