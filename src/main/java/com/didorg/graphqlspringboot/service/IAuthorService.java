package com.didorg.graphqlspringboot.service;

import com.didorg.graphqlspringboot.persistence.domain.Author;
import graphql.schema.DataFetcher;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IAuthorService {
    Mono<String> createAuthor(String authorName, int age, UUID bookId);
    DataFetcher<CompletableFuture<Author>> authorDataFetcher();
}
