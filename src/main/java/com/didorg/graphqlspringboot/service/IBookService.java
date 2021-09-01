package com.didorg.graphqlspringboot.service;

import com.didorg.graphqlspringboot.persistence.domain.Book;
import graphql.schema.DataFetcher;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IBookService {
    DataFetcher<CompletableFuture<Book>> getBook();
    DataFetcher<CompletableFuture<List<Book>>> getBooks();
    DataFetcher<CompletableFuture<String>> createBook();
}
