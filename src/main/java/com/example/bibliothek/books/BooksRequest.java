package com.example.bibliothek.books;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BooksRequest{
    private final String author;
    private final LocalDate published;
    private final String name;
    private final String about;
    private final Integer quantity;
    private final String category;
    private final String language;

    /*public BooksRequest(@RequestParam("book") Optional<Books> books) {
        this.author = books.get().getAuthor();
        this.published = books.get().getPublished();
        this.name = books.get().getName();
        this.about = books.get().getAbout();
        this.quantity = books.get().getQuantity();
        this.category = books.get().getCategory();
        this.language = books.get().getLanguage();
    }*/
}
