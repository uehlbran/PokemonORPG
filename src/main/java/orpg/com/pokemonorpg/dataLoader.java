package orpg.com.pokemonorpg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import orpg.com.pokemonorpg.entities.Image;
import orpg.com.pokemonorpg.entities.trainer.User;
import orpg.com.pokemonorpg.services.ImageService;
import orpg.com.pokemonorpg.services.UserService;

import java.time.LocalDate;

@Component
public class dataLoader implements CommandLineRunner {
    private final UserService userService;
    private final ImageService imageService;

    public dataLoader(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @Override
    public void run(String... args) throws Exception {
        //create image
        Image image = new Image();
        image.setFileLocation("/images/icons/");
        image.setFileName("blackbelt.png");
        //save image
        imageService.save(image);

        User user = new User();
        user.setPassword("password");
        user.setUsername("username");
        user.setDob(LocalDate.now());
        user.setIcon(image);
        //save user
        userService.save(user);
    }
}
