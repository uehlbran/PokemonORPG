package orpg.com.pokemonorpg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Controller
public class UserController {

    private void setupModel(Model model) {
        final LocalDate date = LocalDate.now();
        model.addAttribute("year", date.getYear());
    }

    @GetMapping("/user/{id}")
    public String getUserIndex(@PathVariable Long id, Model model) {
        setupModel(model);
        return "/user/index";
    }
}
