package orpg.com.pokemonorpg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import orpg.com.pokemonorpg.entities.trainer.User;
import orpg.com.pokemonorpg.services.PartyService;
import orpg.com.pokemonorpg.services.UserService;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class UserController {

    private final UserService userService;
    private final PartyService partyService;

    public UserController(UserService userService, PartyService partyService) {
        this.userService = userService;
        this.partyService = partyService;
    }

    private void setupModel(Model model, String username) {
        final User user = userService.findUserByUsername(username);
        final LocalDate date = LocalDate.now();
        model.addAttribute("year", date.getYear());
        model.addAttribute("user", user);
        model.addAttribute("party", partyService.findPartyById(user.getId()).getPokemon());
    }

    @GetMapping("/user/{username}")
    public String getUserIndex(@PathVariable String username, Model model, Principal principal) {
        if (principal != null) System.out.println((principal.toString()));
        setupModel(model, username);
        return "/user/index";
    }
}
