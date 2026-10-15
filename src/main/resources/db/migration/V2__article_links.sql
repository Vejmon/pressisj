CREATE TABLE article_link
(
    article_id UUID NOT NULL,
    name      VARCHAR(255),
    href      VARCHAR(2048),
    fa_icon   VARCHAR(100),

    CONSTRAINT fk_article_link_article
        FOREIGN KEY (article_id) REFERENCES article (id)
);
