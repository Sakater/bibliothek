package com.example.bibliothek.library;

import com.example.bibliothek.appUser.AppUser;
import com.example.bibliothek.books.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/library")
public class LibraryController {
    private final BooksService booksService;
    private final LibraryService libraryService;

    @GetMapping("/borrow")
    public String borrowBook(@RequestBody LibraryRequest request) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser= (AppUser) authentication.getPrincipal();
        return libraryService.borrowBook(booksService.findBookByIsbn(request.getIsbn()), appUser);
    }

    //@PostMapping

    /*@PostMapping("return")
    public String returnBook(@RequestBody LibraryRequest libraryRequest){

    }*/
}
