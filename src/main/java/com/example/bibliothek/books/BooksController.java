package com.example.bibliothek.books;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class BooksController {
    private final BooksService booksService;
    @PostMapping("/books")
    public String addbook(@RequestBody BooksRequest booksRequest){

        return booksService.addNewBook(booksRequest);
    }

    @GetMapping("/books")
    public List<Books> listAllBooks(){
        return booksService.listAllBooks();
    }

    @GetMapping("/book")
    public List<Books> listBook(@RequestBody BooksRequest booksRequest){
        return booksService.loadBookByName(booksRequest);
    }

    @PostMapping("/anything")
    public List<Books> findBySingleEntry(@RequestParam("search") String search) throws Exception {
        return booksService.findBySingleEntry(search);
    }
}
