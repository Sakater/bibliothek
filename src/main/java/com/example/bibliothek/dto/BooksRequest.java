package com.example.bibliothek.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BooksRequest {
    private String author;
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

    private String title;
    private String about;
    private String category;
    private String language;
    private Long isbn;
    private LocalDate published;
    private Integer quantity;



    public boolean isEmpty() {
        if (author.isEmpty() && title.isEmpty() &&
                about.isEmpty() && category.isEmpty() &&
                language.isEmpty() && published == null &&
                quantity == null && isbn.toString().isEmpty()) {
            return true;
        }
        return false;
    }
}
