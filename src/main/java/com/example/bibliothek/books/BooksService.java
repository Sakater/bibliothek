package com.example.bibliothek.books;


import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BooksService {
    private final static String USER_NOT_FOUND_MSG =
            "book with name: %s not found";

    private final BooksRepository booksRepository;


    public List<Books> loadBookByName(Requestr requestr){
        //String name= requestr.getName();
        //String author= requestr.getAuthor();

        return booksRepository.findByNameAndAuthor(requestr.getName(), requestr.getAuthor());
    }


    public String addNewBook(BooksRequest request) {
        Books book = new Books(request.getAuthor(),
                request.getPublished(),
                request.getName(),
                request.getAbout(),
                request.getQuantity(),
                request.getCategory(),
                request.getLanguage());
        booksRepository.save(book);
        return "saved";
    }
    public List<Books> listAllBooks(){
       return booksRepository.findAll();
    }

    /*public List<Books> listfoundbooks(String name, String author){
        return (List<Books>)booksRepository.findByNameAndAuthor(name, author);
        return booksRepository.
    }*/

}


