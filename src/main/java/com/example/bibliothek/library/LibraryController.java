package com.example.bibliothek.library;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/library")
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping("borrow")
    public String borrowBook(@RequestBody LibraryRequest libraryRequest, HttpServletRequest request) {
        Object session = request.changeSessionId().replace("userName", "MMK");
        return libraryService.borrowBook(libraryRequest,session);
    }
}
