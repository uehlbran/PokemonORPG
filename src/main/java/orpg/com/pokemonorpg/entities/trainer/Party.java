package orpg.com.pokemonorpg.entities.trainer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Party {
    @Id
    private Long id;
    @OneToOne
    @MapsId
    private User user;
    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "party", fetch = FetchType.LAZY)
    private List<Pokemon> pokemon = new ArrayList<>();
    @Setter(value = AccessLevel.NONE)
    private int maxPartySize = 6;
    @Setter(value = AccessLevel.NONE)
    private int minPartySize = 1;

    public boolean isPartyFull() {
        return pokemon.size() >= maxPartySize;
    }

    public void addToParty(Pokemon pokemon) {
        if (!isPartyFull()) {
            this.pokemon.add(pokemon);
            pokemon.setParty(this);
        }
    }

    public void removeFromParty(Pokemon pokemon) {
        if (this.pokemon.contains(pokemon)) {
            this.pokemon.remove(pokemon);
            pokemon.setParty(null);
        }
    }

    public static class Factory {
        public static Party createParty(User user) {
            Party party = new Party();
            party.setId(user.getId());
            party.setUser(user);
            return party;
        }
    }
}
