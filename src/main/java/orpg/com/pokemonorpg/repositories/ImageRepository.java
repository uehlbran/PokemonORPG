package orpg.com.pokemonorpg.repositories;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import orpg.com.pokemonorpg.entities.Image;

import javax.persistence.QueryHint;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long> {
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    Optional<Image> findImageById(Long id);
    @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
    Optional<Image> findImageByImageName(String name);
}
