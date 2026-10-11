package no.vejmon.kunstner.config;

import lombok.Getter;
import lombok.Setter;
import no.vejmon.kunstner.models.Page;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "navigation")
@Getter
@Setter
public class NavigationProperties {

    private List<Page> pages;
}
