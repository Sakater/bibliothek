package com.example.bibliothek.controller;

import com.example.bibliothek.dto.BooksResponse;
import com.example.bibliothek.entity.Books;
import com.example.bibliothek.service.BooksService;
import com.example.bibliothek.dto.BooksRequest;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@AllArgsConstructor
public class BooksController {
    private final BooksService booksService;
    // private final HtmlController htmlController;


    //TODO: muss noch bearbeitet werden, damit auf die gleiche Seite, mit der jeweiligen Nachricht geleitet werden kann
    @PostMapping("/addnewbook" /*, consumes = "application/x-www-form-urlencoded"*/)
    public String addbook(@RequestBody BooksRequest booksRequest /*Model model*/) {
        //new BooksRequest(book);
        String status = booksService.addNewBook(booksRequest);
        return status;
    }

    @GetMapping("/books")
    public List<Books> listAllBooks() {
        return booksService.listAllBooks();
    }

    @GetMapping("/bookname")
    public List<BooksRequest> listBook(@RequestBody BooksRequest request) {
        return booksService.loadBookByName(request);
    }

    @GetMapping("/anything")
    public List<BooksRequest> findBySingleEntry(@RequestParam("search") String search) throws Exception {
        return booksService.findBySingleEntry(search);
    }
}
