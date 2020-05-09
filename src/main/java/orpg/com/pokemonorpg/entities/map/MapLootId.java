package orpg.com.pokemonorpg.entities.map;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class MapLootId implements Serializable {
    private Long mapId;
    private Long pokemonId;

    public MapLootId(Long mapId, Long pokemonId) {
        this.mapId = mapId;
        this.pokemonId = pokemonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapLootId)) return false;
        MapLootId that = (MapLootId) o;
        return mapId != null && pokemonId != null
                && (pokemonId.equals(that.pokemonId)) && (mapId.equals(that.mapId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapId, pokemonId);
    }
}
