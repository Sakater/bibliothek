package com.example.bibliothek;

import com.example.bibliothek.books.Books;
import com.example.bibliothek.books.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@AllArgsConstructor
public class HtmlController {

    private final BooksService booksService;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("name", "My Name");
        return "index";
    }

    @GetMapping(path = "/addbook")
    public String addbook(Model model) {
        model.addAttribute("name", "My Name");
        return "addbook";
    }

    @GetMapping(path = "/loadall")
    public String loadallbooks(Model model) {
        List<Books> booksList = booksService.listAllBooks();
        model.addAttribute("booklist", booksList);
        return "allbooks";
    }


}
