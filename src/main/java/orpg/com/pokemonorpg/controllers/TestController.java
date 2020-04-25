package orpg.com.pokemonorpg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class TestController {

    @GetMapping({"", "/index"})
    public String getIndexPage(Model model) {
        final String homeFragmentPath = "fragments/index/home :: home";
        setupModel(model, homeFragmentPath);
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        final String homeFragmentPath = "fragments/index/login :: login";
        setupModel(model, homeFragmentPath);
        return "index";
    }

    private void setupModel(Model model, String fragmentPath) {
        final LocalDate date = LocalDate.now();
        model.addAttribute("year", date.getYear());
        model.addAttribute("page", fragmentPath);
    }

    @GetMapping("/legal")
    public String getLegalPage(Model model) {
        final String legalFragmentPath = "fragments/index/legal :: legal";
        setupModel(model, legalFragmentPath);
        return "index";
    }

}
