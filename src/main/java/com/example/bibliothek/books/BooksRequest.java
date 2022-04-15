package com.example.bibliothek.books;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
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
    private final Long isbn;
}
