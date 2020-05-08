package orpg.com.pokemonorpg.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orpg.com.pokemonorpg.entities.trainer.Party;
import orpg.com.pokemonorpg.repositories.PartyRepository;

@Service
public class PartyService {
    private final PartyRepository repository;

    public PartyService(PartyRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Party findPartyById(Long id) {
        return repository.findPartyById(id).orElseThrow(() -> new RuntimeException("Unable to find party!"));
    }
    @Transactional
    public Party save(Party party) {
        return repository.save(party);
    }
}
