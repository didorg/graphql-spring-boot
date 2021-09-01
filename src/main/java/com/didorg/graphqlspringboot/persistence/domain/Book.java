package com.didorg.graphqlspringboot.persistence.domain;

import com.didorg.graphqlspringboot.constant.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("books")
public class Book {
    @Id
    private UUID id;
    private String name;
    private int pages;
    private Category category;


    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

}
