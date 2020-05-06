package orpg.com.pokemonorpg.entities.map;

import lombok.Data;
import lombok.NoArgsConstructor;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
@NoArgsConstructor
public class MapLoot {
    @EmbeddedId
    MapLootId id;

    @ManyToOne
    @MapsId("mapId")
    private Map map;

    @ManyToOne
    @MapsId("pokemonId")
    private Pokemon pokemon;

    public MapLoot(Map map, Pokemon pokemon) {
        this.map = map;
        this.pokemon = pokemon;
        this.id = new MapLootId(map.getId(), pokemon.getId());
    }

    //TODO: Add equals and hashcode methods
}
