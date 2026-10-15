package no.vejmon.kunstner.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ArticleLink {

    private String name;
    private String href;
    private FaIcon faIcon = FaIcon.faLink;

}

