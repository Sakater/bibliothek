package com.example.bibliothek.books;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class BooksRequest {
    private final String author;
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate published;
    private final String title;
    private final String about;
    private final Integer quantity;
    private final String category;
    private final String language;
    private final Long isbn;

    public boolean isEmpty() {
        if (this.author.isEmpty() && this.title.isEmpty() &&
                this.about.isEmpty()  && this.category.isEmpty() &&
                this.language.isEmpty() && this.published==null &&
                this.quantity == null) {
            return true;
        }
        return false;
    }
}
