package orpg.com.pokemonorpg;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import orpg.com.pokemonorpg.entities.Gender;
import orpg.com.pokemonorpg.entities.Image;
import orpg.com.pokemonorpg.entities.pokemon.ElementType;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;
import orpg.com.pokemonorpg.entities.pokemon.PokemonType;
import orpg.com.pokemonorpg.entities.pokemon.Species;
import orpg.com.pokemonorpg.entities.trainer.User;
import orpg.com.pokemonorpg.entities.trainer.UserPokemon;
import orpg.com.pokemonorpg.entities.trainer.UserPokemonId;
import orpg.com.pokemonorpg.services.*;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    private void createSpeciesSqlFile(String pokedexNumber,
                                      String pokemonName,
                                      ElementType type1,
                                      ElementType type2,
                                      String imageId,
                                      String captureRate,
                                      String generation,
                                      PokemonType pokemonType) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data.sql", true));
        String sql = String.format(
                "INSERT INTO species " +
                        "(pokedex_number, name, type1, type2, icon_id, capture_rate, generation, pokemon_type) " +
                        "VALUES " +
                        "(%s, %s, %s, %s, %s, %s, %s, %s);",
                String.format("'%s'", pokedexNumber),
                String.format("'%s'", pokemonName),
                String.format("'%s'", type1),
                String.format("'%s'", type2),
                String.format("'%s'", imageId),
                String.format("'%s'", captureRate),
                String.format("'%s'", generation),
                String.format("'%s'", pokemonType));
        writer.append(sql);
        writer.newLine();
        writer.close();
    }

    private void createPokemonImageSqlFile(String fileName, String imageName, String fileLocation) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data.sql", true));
        String sql = String.format(
                "INSERT INTO image " +
                        "(file_name, file_location, file_extension, image_name) " +
                        "VALUES " +
                        "(%s, %s, %s, %s);",
                String.format("'%s'", fileName),
                String.format("'%s'", fileLocation),
                String.format("'%s'", ".png"),
                String.format("'%s'", imageName));
        writer.append(sql);
        writer.newLine();
        writer.close();
    }

    private void createTrainerImageSqlFile(String imageId) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data.sql", true));
        String sql = String.format(
                "INSERT INTO image " +
                        "(id, file_name, file_location, file_extension) " +
                        "VALUES " +
                        "(%s, %s, %s, %s);",
                String.format("'%s'", imageId),
                String.format("'%s'", imageId),
                String.format("'%s'", "/images/sprites/trainer/"),
                String.format("'%s'", ".png"));
        writer.append(sql);
        writer.newLine();
        writer.close();
    }

    private void loadSpecies() throws IOException, CsvValidationException {
        final String filepath = "src/main/resources/static/csv/pokemon.csv";
        CSVReader reader = new CSVReader(new FileReader(filepath));
        reader.readNext();
        while(reader.peek() != null) {
            String[] row = reader.readNext();
            //create normal pokemon image
            createPokemonImageSqlFile(row[0], row[1], "/images/sprites/pokemon/normal/");
            //add image to database
            Image normalImage = new Image("/images/sprites/pokemon/normal/", row[0], ".png", row[1]);
            imageService.save(normalImage);
            //create shiny pokemon image
            createPokemonImageSqlFile(row[0], "Shiny" + row[1], "/images/sprites/pokemon/shiny/");
            //add image to database
            Image shinyImage = new Image("/images/sprites/pokemon/shiny/", row[0], ".png", "Shiny" + row[1]);
            imageService.save(shinyImage);
            //create dark pokemon image
            createPokemonImageSqlFile("?", "Dark" + row[1], "/images/sprites/pokemon/dark/");
            //add image to database
            Image darkImage = new Image("/images/sprites/pokemon/dark/", row[0], ".png", "Dark" + row[1]);
            imageService.save(darkImage);
            //create golden pokemon image
            createPokemonImageSqlFile("?", "Golden" + row[1], "/images/sprites/pokemon/golden/");
            //add image to database
            Image goldenImage = new Image("/images/sprites/pokemon/golden/", row[0], ".png", "Golden" + row[1]);
            imageService.save(goldenImage);
            //create normal species
            createSpeciesSqlFile(
                    row[0],
                    row[1],
                    ElementType.valueOf(row[2]),
                    ElementType.valueOf(row[3]),
                    imageService.findImageByImageName(row[1]).getId().toString(),
                    row[4],
                    row[5],
                    PokemonType.Normal);
            //create shiny species
            createSpeciesSqlFile(
                    row[0],
                    "Shiny" + row[1],
                    ElementType.valueOf(row[2]),
                    ElementType.valueOf(row[3]),
                    imageService.findImageByImageName("Shiny" + row[1]).getId().toString(),
                    row[4],
                    row[5],
                    PokemonType.Shiny);
            //create dark species
            createSpeciesSqlFile(
                    row[0],
                    "Dark" + row[1],
                    ElementType.valueOf(row[2]),
                    ElementType.valueOf(row[3]),
                    imageService.findImageByImageName("Dark" + row[1]).getId().toString(),
                    row[4],
                    row[5],
                    PokemonType.Dark);
            //create golden species
            createSpeciesSqlFile(
                    row[0],
                    "Golden" + row[1],
                    ElementType.valueOf(row[2]),
                    ElementType.valueOf(row[3]),
                    imageService.findImageByImageName("Golden" + row[1]).getId().toString(),
                    row[4],
                    row[5],
                    PokemonType.Golden);
        }
    }

    private void createSpecies(String[] values) {
        Species species = new Species();
        species.setId(Long.parseLong(values[0]));
        species.setName(values[1]);
        species.setType1(ElementType.valueOf(values[2]));
        species.setType2(ElementType.valueOf(values[3]));
        species.setCaptureRate(Integer.parseInt(values[4]));
        species.setGeneration(Integer.parseInt(values[5]));
        speciesService.save(species);
    }

    @Override
    public void run(String... args) throws Exception {
        //loadSpecies();

        //create pokemon icon
        Image pokemonIcon = imageService.findImageById(1L);

        //create Pokemon species
        Species species = speciesService.findSpeciesById(25L);

        //create pokemon
        Pokemon pokemon = new Pokemon();
        pokemon.setSpecies(species);
        pokemon.setGender(Gender.MALE);
        //save pokemon
        pokemonService.save(pokemon);

        //create user
        User user = new User();
        user.setPassword("password");
        user.setUsername("username");
        user.setDob(LocalDate.now());
        user.setIcon(pokemonIcon);
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
