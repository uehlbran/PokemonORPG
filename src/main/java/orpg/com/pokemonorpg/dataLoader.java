package orpg.com.pokemonorpg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import orpg.com.pokemonorpg.entities.Gender;
import orpg.com.pokemonorpg.entities.Image;
import orpg.com.pokemonorpg.entities.pokemon.Generation;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;
import orpg.com.pokemonorpg.entities.pokemon.Species;
import orpg.com.pokemonorpg.entities.pokemon.Type;
import orpg.com.pokemonorpg.entities.trainer.User;
import orpg.com.pokemonorpg.entities.trainer.UserPokemon;
import orpg.com.pokemonorpg.entities.trainer.UserPokemonId;
import orpg.com.pokemonorpg.services.*;

import java.time.LocalDate;

@Component
public class dataLoader implements CommandLineRunner {
    private final UserService userService;
    private final ImageService imageService;
    private final SpeciesService speciesService;
    private final PokemonService pokemonService;
    private final UserPokemonService userPokemonService;

    public dataLoader(UserService userService,
                      ImageService imageService,
                      SpeciesService speciesService,
                      PokemonService pokemonService,
                      UserPokemonService userPokemonService) {
        this.userService = userService;
        this.imageService = imageService;
        this.speciesService = speciesService;
        this.pokemonService = pokemonService;
        this.userPokemonService = userPokemonService;
    }

    @Override
    public void run(String... args) throws Exception {
        //create pokemon icon
        Image pokemonIcon = new Image();
        pokemonIcon.setFileLocation("/images/");
        pokemonIcon.setFileName("1.png");
        //save image
        imageService.save(pokemonIcon);

        //create Pokemon species
        Species species = new Species();
        species.setIcon(pokemonIcon);
        species.setName("Pikachu");
        species.setGender(Gender.MALE);
        species.setCaptureRate(100);
        species.setGeneration(Generation.GENERATION_1);
        species.setHeldItem(null);
        species.setId(25);
        species.setType1(Type.Electric);
        species.setType2(Type.None);
        //save species
        speciesService.save(species);

        //create pokemon
        Pokemon pokemon = new Pokemon();
        pokemon.setSpecies(species);
        //save pokemon
        pokemonService.save(pokemon);

        //create user icon
        Image image = new Image();
        image.setFileLocation("/images/icons/");
        image.setFileName("blackbelt.png");
        //save image
        imageService.save(image);

        //create user
        User user = new User();
        user.setPassword("password");
        user.setUsername("username");
        user.setDob(LocalDate.now());
        user.setIcon(image);
        //save user
        userService.save(user);


        //create user_pokemon_id
        UserPokemonId userPokemonId = new UserPokemonId();
        userPokemonId.setUserId(user.getId());
        userPokemonId.setPokemonId(pokemon.getId());
        //save user_pokemon_id

        //create user_pokemon
        UserPokemon userPokemon = new UserPokemon();
        userPokemon.setPokemon(pokemon);
        userPokemon.setUser(user);
        userPokemon.setId(userPokemonId);
        //save user_pokemon
        userPokemonService.save(userPokemon);

    }
}
