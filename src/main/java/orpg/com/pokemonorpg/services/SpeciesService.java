package orpg.com.pokemonorpg.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orpg.com.pokemonorpg.entities.pokemon.Species;
import orpg.com.pokemonorpg.repositories.SpeciesRepository;

@Service
public class SpeciesService {
    private final SpeciesRepository repository;

    public SpeciesService(SpeciesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Species findSpeciesById(Long id) {
        return repository.findSpeciesById(id).orElseThrow(() -> new RuntimeException("Unable to find species"));
    }
    @Transactional
    public Species save(Species species) {
        return repository.save(species);
    }
    @Transactional
    public Species findSpeciesByName(String name) {
        return repository.findSpeciesByName(name).orElseThrow(() -> new RuntimeException("Unable to find species"));
    }
}
