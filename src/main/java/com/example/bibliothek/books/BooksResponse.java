package com.example.bibliothek.books;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@Data
@RequiredArgsConstructor
public class BooksResponse {
    private String author;
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate published;
    private String title;
    private String about;
    private Integer quantity;
    private String category;
    private String language;
    private Long isbn;
}
