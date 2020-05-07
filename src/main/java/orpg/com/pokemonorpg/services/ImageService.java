package orpg.com.pokemonorpg.services;

import org.springframework.stereotype.Service;
import orpg.com.pokemonorpg.entities.Image;
import orpg.com.pokemonorpg.repositories.ImageRepository;

@Service
public class ImageService {
    private final ImageRepository repository;

    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    public Image findImageById(Long id) {
        return repository.findImageById(id).orElseThrow(() -> new RuntimeException("Unable to find image"));
    }

    public Image findImageByImageName(String name) {
        return repository.findImageByImageName(name).orElseThrow(() -> new RuntimeException("Unable to find image"));
    }

    public Image save(Image image) {
        return repository.save(image);
    }
}
