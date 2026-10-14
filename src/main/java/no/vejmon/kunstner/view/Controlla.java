package no.vejmon.kunstner.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.vejmon.kunstner.config.NavigationProperties;
import no.vejmon.kunstner.models.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class Controlla {

    private final Map<String, String> pages;

    public Controlla(NavigationProperties nav) {
        pages = new LinkedHashMap<>();
        for (Page page: nav.getPages()) {
            pages.put(page.getName(), page.getUrl());
        }

    }

    @GetMapping("/admin")
    public String admin(Model model) throws JsonProcessingException {
        try {
            pages.put("Admin", "/admin");
            String pagesString = new ObjectMapper().writeValueAsString(pages);
            model.addAttribute("pages", pagesString);
            model.addAttribute("isAdmin", true);
        }
        finally {
            pages.remove("Admin");
        }
        return "index";
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) throws JsonProcessingException {
        model.addAttribute("pages", new ObjectMapper().writeValueAsString(pages));
        return "index";
    }

}
