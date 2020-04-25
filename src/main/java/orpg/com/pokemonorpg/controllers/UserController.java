package orpg.com.pokemonorpg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class UserController {

    private void setupModel(Model model) {
        final LocalDate date = LocalDate.now();
        model.addAttribute("year", date.getYear());
    }

    @GetMapping("/user")
    public String getUserIndex(Model model) {
        setupModel(model);
        return "/user/index";
    }
}
