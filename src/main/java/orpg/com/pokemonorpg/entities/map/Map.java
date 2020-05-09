package orpg.com.pokemonorpg.entities.map;

import lombok.*;
import orpg.com.pokemonorpg.entities.Image;

import javax.persistence.*;

@Entity
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Transient //TODO: implement image properly
    private Image image;
}
