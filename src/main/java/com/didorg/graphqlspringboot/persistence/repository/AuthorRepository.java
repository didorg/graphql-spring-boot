package com.didorg.graphqlspringboot.persistence.repository;

import com.didorg.graphqlspringboot.persistence.domain.Author;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class AuthorRepository {

    private final R2dbcEntityTemplate template;

    public AuthorRepository(R2dbcEntityTemplate template) {
        this.template = template;
    }

    public Mono<UUID> createAuthor(Author author) {
        UUID authorId = UUID.randomUUID();
        author.setId(authorId);
        return template.insert(Author.class).using(author).thenReturn(authorId);
    }

    public Mono<Author> getAuthor(UUID bookId) {
        return template.select(Author.class).matching(Query.query(Criteria.where("book_id").is(bookId))).one();
    }
}
