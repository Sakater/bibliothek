package com.example.bibliothek.books;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Books{

    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    private String author;
    private LocalDate published;
    private String name;
    private String about;
    private Integer quantity;
    private String category;
    private String language;
    private Long isbn;


    public Books(String author,
                 LocalDate published,
                 String name,
                 String about,
                 Integer quantity,
                 String category,
                 String language) {
        this.author = author;
        this.published = published;
        this.name = name;
        this.about = about;
        this.quantity = quantity;
        this.category = category;
        this.language = language;
    }

    public boolean isAvailable(){
        return true;
    }
}



