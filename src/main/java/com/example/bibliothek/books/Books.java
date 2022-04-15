package com.example.bibliothek.books;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Books{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String author;
    private LocalDate published;
    private String name;
    private String about;
    private Integer quantity;
    private String category;
    private String language;
    private Long isbn;


    public boolean isAvailable(){
        return true;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", published=" + published +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", isbn=" + isbn +
                '}';
    }
}



