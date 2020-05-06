package orpg.com.pokemonorpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PokemonOrpgApplication {
	public static void main(String[] args) {
		SpringApplication.run(PokemonOrpgApplication.class, args);
	}

}
