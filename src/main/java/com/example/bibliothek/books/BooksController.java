package com.example.bibliothek.books;

import com.example.bibliothek.HtmlController;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@RestController
@AllArgsConstructor
public class BooksController {
    private final BooksService booksService;
    private final HtmlController htmlController;


    //TODO: muss noch bearbeitet werden, damit auf die gleiche Seite, mit der jeweiligen Nachricht geleitet werden kann
    @PostMapping(path = "/addnewbook", consumes = "application/x-www-form-urlencoded")
    public RedirectView addbook(BooksRequest booksRequest, Model model) {
        //new BooksRequest(book);
        String status = booksService.addNewBook(booksRequest);
        if (status == "saved") {
            model.addAttribute("status", "saved");
        } else {
            model.addAttribute("status", "you have to fill at least one field");
        }
        htmlController.addbook(model);
        return new RedirectView("addbook");

    }

    @GetMapping("/books")
    public List<Books> listAllBooks() {
        return booksService.listAllBooks();
    }

    @GetMapping("/book")
    public List<Books> listBook(@RequestBody Requestr requestr) {
        return booksService.loadBookByName(requestr);
    }

    @PostMapping("/anything")
    public List<Books> findBySingleEntry(@RequestParam("search") String search) throws Exception {
        return booksService.findBySingleEntry(search);
    }
}
