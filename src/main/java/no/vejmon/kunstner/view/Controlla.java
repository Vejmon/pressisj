package no.vejmon.kunstner.view;

import no.vejmon.kunstner.config.NavigationProperties;
import no.vejmon.kunstner.models.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class Controlla {

    private final String names;

    public Controlla(NavigationProperties nav) {
        names = nav.getPages().stream()
                .map(Page::getName)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("pages", names + ", Admin");
        return "index";}

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("pages", names);
        return "index";
    }

}
