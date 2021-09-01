package com.didorg.graphqlspringboot.service;

import com.didorg.graphqlspringboot.constant.Category;
import com.didorg.graphqlspringboot.persistence.domain.Book;
import com.didorg.graphqlspringboot.persistence.repository.BookRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public DataFetcher<CompletableFuture<Book>> getBook() {
        return env -> {
            String bookId = env.getArgument("id");
            return bookRepository.getBook(UUID.fromString(bookId)).toFuture();
        };
    }

    public DataFetcher<CompletableFuture<List<Book>>> getBooks() {
        return env -> bookRepository.getBooks().collectList().toFuture();
    }

    public DataFetcher<CompletableFuture<String>> createBook() {
        return env -> {
            String bookName = env.getArgument("bookName");
            String authorName = env.getArgument("authorName");

            int pages = env.getArgument("pages");
            int age = env.getArgument("age");
            Category category = Category.valueOf(env.getArgument("category"));

            Book book = new Book();
            book.setName(bookName);
            book.setPages(pages);
            book.setCategory(category);

            return bookRepository.createBook(book).flatMap(
                            bookId -> authorService.createAuthor(authorName, age, bookId)
                                    .map(authorId -> bookId.toString()))
                    .toFuture();
        };
    }
}
