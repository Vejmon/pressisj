package no.vejmon.kunstner.view;

import no.vejmon.kunstner.models.FaIcon;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@RepositoryRestController
public class ArticleRestController {

    @GetMapping("/articles/available-icons")
    public @ResponseBody List<FaIcon> getAvailableIcons() {
        return List.of(FaIcon.values());
    }
}
