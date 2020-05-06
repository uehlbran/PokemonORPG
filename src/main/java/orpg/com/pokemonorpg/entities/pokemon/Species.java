package orpg.com.pokemonorpg.entities.pokemon;

import orpg.com.pokemonorpg.entities.Gender;
import orpg.com.pokemonorpg.entities.item.Item;

import javax.persistence.*;

@Entity
public class Species {
    @Id
    private long pokedexNumber;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Type type1;
    @Enumerated(value = EnumType.STRING)
    private Type type2;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private double captureRate;
    private double genderRate;
    @Enumerated(value = EnumType.STRING)
    private Generation generation;
    @Transient //TODO: Add holdable items
    private Item heldItem;
    private boolean hasEvolution;
    private int evolvesAt;
    @Transient //TODO: Add evolution lines
    private Pokemon nextEvolution;
}
