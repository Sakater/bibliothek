package com.example.bibliothek.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private UUID id;
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
        this.category = category;
        this.language = language;
        this.isbn = isbn;
    }

    public boolean isAvailable() {
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
                ", isbn=" + isbn +
                '}';
    }
}



