package no.vejmon.kunstner.repo;

import no.vejmon.kunstner.models.Article;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepoConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry corsRegistry){
        config.exposeIdsFor(Article.class);
        config.setDefaultPageSize(10);
        config.setBasePath("/api");
    }
}
