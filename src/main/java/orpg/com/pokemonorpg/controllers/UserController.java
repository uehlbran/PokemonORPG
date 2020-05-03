package orpg.com.pokemonorpg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import orpg.com.pokemonorpg.entities.trainer.User;
import orpg.com.pokemonorpg.services.UserService;

import java.time.LocalDate;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private void setupModel(Model model, String username) {
        final User user = userService.findUserByUsername(username);
        final LocalDate date = LocalDate.now();
        model.addAttribute("year", date.getYear());
        model.addAttribute("user", user);
    }

    @GetMapping("/user/{username}")
    public String getUserIndex(@PathVariable String username, Model model) {
        setupModel(model, username);
        return "/user/index";
    }
}
