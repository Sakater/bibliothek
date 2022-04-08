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
    public String addbook(@RequestBody BooksRequest booksRequest/*,@RequestParam("book") String book*/){
        //new BooksRequest(book);
        booksService.addNewBook(booksRequest);
        return "saved";
    }

    @GetMapping("/books")
    public List<Books> listAllBooks(){
        return booksService.listAllBooks();
    }

    @GetMapping("/book")
    public List<Books> listBook(@RequestBody Requestr requestr){
        return booksService.loadBookByName(requestr);

    }
}
