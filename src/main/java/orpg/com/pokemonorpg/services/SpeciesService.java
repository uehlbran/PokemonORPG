package orpg.com.pokemonorpg.services;

import org.springframework.stereotype.Service;
import orpg.com.pokemonorpg.entities.pokemon.Species;
import orpg.com.pokemonorpg.repositories.SpeciesRepository;

@Service
public class SpeciesService {
    private final SpeciesRepository repository;

    public SpeciesService(SpeciesRepository repository) {
        this.repository = repository;
    }

    public Species findSpeciesById(Long id) {
        return repository.findSpeciesById(id).orElseThrow(() -> new RuntimeException("Unable to find species"));
    }

    public Species save(Species species) {
        return repository.save(species);
    }
}
