package com.example.bibliothek.books;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Books {

    @Id
    @SequenceGenerator(
            name = "books_sequence",
            sequenceName = "books_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "books_sequence")
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;
    private String author;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate published;
    private String title;
    private String about;
    private Integer quantity;
    private String category;
    private String language;
    private Long isbn;



    public Books(String author,
                 LocalDate published,
                 String title,
                 String about,
                 Integer quantity,
                 String category,
                 String language,
                 Long isbn) {
        this.author = author;
        this.published = published;
        this.title = title;
        this.about = about;
        this.quantity = quantity;
        this.category =  category;
        this.language = language;
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        if ((this.quantity==null||this.quantity<1)){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", published=" + published +
                ", name='" + title + '\'' +
                ", about='" + about + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", isbn='" + isbn + '\'' +
                "}";
    }
}



