package no.vejmon.kunstner.repo;

import no.vejmon.kunstner.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
public interface ArticleRepository extends JpaRepository<Article, UUID> {
    @RestResource(path = "created")
    Page<Article> findAllByOrderByCreatedDesc(Pageable pageable);
}
