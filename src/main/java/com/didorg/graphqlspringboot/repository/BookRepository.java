package com.didorg.graphqlspringboot.repository;

import com.didorg.graphqlspringboot.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class BookRepository {
    @Autowired
    private R2dbcEntityTemplate template;

    public Mono<Book> getBook(UUID id) {
        return template.select(Book.class).matching(Query.query(Criteria.where("id").is(id))).one();
    }

    public Flux<Book> getBooks() {
        return template.select(Book.class).all();
    }

    public Mono<UUID> createBook(Book book) {
        UUID bookId = UUID.randomUUID();
        book.setId(bookId);
        return template.insert(Book.class).using(book).thenReturn(bookId);
    }
}
