package com.example.bibliothek.books;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
//@RequestMapping("api/v1/books")
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

    // @CrossOrigin("*")
    @GetMapping("/books")
    public String/*List<Books>*/ listAllBooks() {
        return "Successful";//booksService.listAllBooks();
    }

    @GetMapping("/bookname")
    public List<BooksRequest> listBook(@RequestBody BooksRequest request) {
        return booksService.loadBookByName(request);
    }

    @GetMapping("/anything")
    public List<BooksRequest> findBySingleEntry(@RequestParam("search") String search) throws Exception {
        return booksService.findBySingleEntry(search);
    }

    @PostMapping("/addnewbooks")
    public void add() {
        booksService.addbunchofnewbooks();
    }
}
