package com.example.bibliothek.library;

import com.example.bibliothek.appUser.AppUser;
import com.example.bibliothek.appUser.AppUserService;
import com.example.bibliothek.books.BooksService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class LibraryService {

    private final AppUserService appUserService;
    private final BooksService booksService;
    private final LibraryRepository libraryRepository;

    public String borrowBook(LibraryRequest request, Object session) {
        String userName = session.toString();

        libraryRepository.save(new Library(
                appUserService.findByUserName(userName),
                booksService.findBookByIsbn(request.getIsbn())
        ));
        // Long appUserId= appUserService.findUserById();
        return "borrowed";
    }
}
