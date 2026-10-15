package no.vejmon.kunstner.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String subtitle;
    private String text;
    @CreationTimestamp
    private Date created;
    @ElementCollection
    @CollectionTable(
            name = "article_link",
            joinColumns = @JoinColumn(name = "article_id")
    )
    private List<ArticleLink> links = new ArrayList<>();


}
