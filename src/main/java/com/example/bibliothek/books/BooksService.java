package com.example.bibliothek.books;


import lombok.AllArgsConstructor;
import org.hibernate.criterion.Example;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BooksService {
    private final static String USER_NOT_FOUND_MSG =
            "book with name: %s not found";

    private final BooksRepository booksRepository;


    public List<Books> findBySingleEntry(String search) throws Exception {
        List<Books> found = booksRepository.findBySingleEntry(search);
        if (!found.isEmpty()) {
            return found;
        } else throw new Exception("no matches");
    }

    public List<Books> loadBookByName(BooksRequest booksRequest) {

        //String name= requestr.getName();
        //String author= requestr.getAuthor();

        return booksRepository.findByNameAndAuthor(booksRequest.getName(), booksRequest.getAuthor());
    }


    public String addNewBook(BooksRequest request) {
        Books book = new Books(
                UUID.randomUUID(),
                request.getAuthor(),
                request.getPublished(),
                request.getName(),
                request.getAbout(),
                request.getQuantity(),
                request.getCategory(),
                request.getLanguage(),
                request.getIsbn());

        booksRepository.save(book);
        return "saved " + request.getName() +" from " +request.getAuthor();
    }

    public List<Books> listAllBooks() {
        return booksRepository.findAll();
    }

    /*public List<Books> listfoundbooks(String name, String author){
        return (List<Books>)booksRepository.findByNameAndAuthor(name, author);
        return booksRepository.
    }*/

}


