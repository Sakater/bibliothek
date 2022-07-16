package com.example.bibliothek.service;


import com.example.bibliothek.dto.BooksResponse;
import com.example.bibliothek.entity.Books;
import com.example.bibliothek.dto.BooksRequest;
import com.example.bibliothek.repository.BooksRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BooksService {
    private final static String USER_NOT_FOUND_MSG =
            "book with name: %s not found";

    private final BooksRepository booksRepository;


    public List<BooksRequest> findBySingleEntry(String search) throws Exception {
        List<BooksRequest> found = booksRepository.findBySingleEntry(search);
        if (!found.isEmpty()) {
            return found;
        } else throw new Exception("no matches");
    }

    public List<BooksRequest> loadBookByName(BooksRequest request) {

        //String name= requestr.getTitle();
        //String author= requestr.getAuthor();

        return booksRepository.findByName(request.getTitle());
    }


    public String addNewBook(@NotNull BooksRequest request) {
        //if (!request.isEmpty()){
        booksRepository.save(new Books(request.getAuthor(),
                    request.getPublished(),
                    request.getTitle(),
                    request.getAbout(),
                    request.getQuantity(),
                    request.getCategory(),
                    request.getLanguage(),
                    request.getIsbn()));
            return "saved";
       // }
        //return "empty";
    }

    public List<Books> listAllBooks() {
        return booksRepository.findAll();
    }

    /*public List<Books> listfoundbooks(String name, String author){
        return (List<Books>)booksRepository.findByNameAndAuthor(name, author);
        return booksRepository.
    }*/

}


