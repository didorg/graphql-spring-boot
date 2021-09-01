package com.didorg.graphqlspringboot.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("authors")
public class Author {
    @Id
    private UUID id;
    private String name;
    private int age;
    @Column("book_id")
    private UUID bookId;

}
