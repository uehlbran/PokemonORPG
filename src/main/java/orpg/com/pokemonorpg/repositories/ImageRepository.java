package orpg.com.pokemonorpg.repositories;

import org.springframework.data.repository.CrudRepository;
import orpg.com.pokemonorpg.entities.Image;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<Image> findImageById(Long id);
    Optional<Image> findImageByImageName(String name);
}
