package com.example.bibliothek.controller;

import com.example.bibliothek.entity.Books;
import com.example.bibliothek.service.BooksService;
import com.example.bibliothek.dto.BooksRequest;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class BooksController {
    private final BooksService booksService;
    private final HtmlController htmlController;


    //TODO: muss noch bearbeitet werden, damit auf die gleiche Seite, mit der jeweiligen Nachricht geleitet werden kann
    @PostMapping(path = "/addnewbook", consumes = "application/x-www-form-urlencoded")
    public String addbook(BooksRequest booksRequest, Model model) {
        //new BooksRequest(book);
        String status = booksService.addNewBook(booksRequest);
        if (status == "saved") {
            model.addAttribute("status", "saved");
        } else {
            model.addAttribute("status", "you have to fill in at least one field");
        }
        htmlController.addbook(model);
        return model.getAttribute("status").toString();

    }

    @GetMapping("/books")
    public List<Books> listAllBooks() {
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
