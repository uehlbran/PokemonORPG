package orpg.com.pokemonorpg.entities.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import orpg.com.pokemonorpg.entities.Gender;
import orpg.com.pokemonorpg.entities.Image;
import orpg.com.pokemonorpg.entities.item.Item;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Species {
    @Id
    private long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Type type1;
    @Enumerated(value = EnumType.STRING)
    private Type type2;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private double captureRate;
    @Transient //TODO: Create system for determining pokemon gender ratio
    private double genderRate;
    @Enumerated(value = EnumType.STRING)
    private Generation generation;
    @Transient //TODO: Add holdable items
    private Item heldItem;
    //TODO: Add evolution lines
    @OneToOne
    @JoinColumn(name = "icon_id")
    private Image icon;
}
