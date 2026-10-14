package no.vejmon.kunstner.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ArticleLink {

    private String name;
    private String href;
    private FaIcon faIcon;

}

