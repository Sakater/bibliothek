package com.example.bibliothek.books;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate published;
    private String title;
    private String about;
    private Integer quantity;
    private String category;
    private String language;


    public Books(String author,
                 LocalDate published,
                 String title,
                 String about,
                 Integer quantity,
                 String category,
                 String language) {
        this.author = author;
        this.published = published;
        this.title = title;
        this.about = about;
        this.quantity = quantity;
        this.category = category;
        this.language = language;
    }

    public boolean isAvailable(){
        return true;
    }
}



