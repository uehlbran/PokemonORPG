package orpg.com.pokemonorpg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import orpg.com.pokemonorpg.entities.trainer.User;
import orpg.com.pokemonorpg.services.UserService;

import java.time.LocalDate;

@Component
public class dataLoader implements CommandLineRunner {
    private final UserService service;

    public dataLoader(UserService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setPassword("password");
        user.setUsername("username");
        user.setDob(LocalDate.now());
        service.save(user);
    }
}
