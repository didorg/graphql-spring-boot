package com.didorg.graphqlspringboot.service;

import com.didorg.graphqlspringboot.persistence.domain.Author;
import com.didorg.graphqlspringboot.persistence.domain.Book;
import com.didorg.graphqlspringboot.persistence.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthorService implements IAuthorService{

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Mono<String> createAuthor(String authorName, int age, UUID bookId) {
        Author author = new Author();
        author.setAge(age);
        author.setName(authorName);
        author.setBookId(bookId);
        return repository.createAuthor(author).map(Object::toString);
    }

    public DataFetcher<CompletableFuture<Author>> authorDataFetcher() {
        return env -> {
            Book book = env.getSource();
            return repository.getAuthor(book.getId()).toFuture();
        };
    }
}
