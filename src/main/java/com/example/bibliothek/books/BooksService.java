package com.example.bibliothek.books;



import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


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

        return booksRepository.findByNameContaining(request.getTitle());
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

    public void addbunchofnewbooks() {
        int i = 0;
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long leftLimit = 1000000000L;
        long rightLimit = 9999999999L;
        int lowerBound = 0;
        int upperBound = 10;
        Random random = new Random();
        Faker faker = new Faker(new Locale("de"));
        while (i < 50) {
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
            String title = faker.book().title();
            String about = faker.book().publisher();
            String category = faker.book().genre();
            String author = faker.book().author();
            String language = faker.country().name();
            Integer quantity = random.nextInt(0, 10);
            Long isbn = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            this.addNewBook(new BooksRequest(
                    author,
                    title,
                    about,
                    category,
                    language,
                    isbn,
                    randomDate,
                    quantity));
            i++;
        }

    }

    public Books findBookByIsbn(Long isbn){
        return booksRepository.findBooksByIsbn(isbn);
    }

    public List<Books> listAllBooks() {
        return booksRepository.findAll();
    }

    /*public List<Books> listfoundbooks(String name, String author){
        return (List<Books>)booksRepository.findByNameAndAuthor(name, author);
        return booksRepository.
    }*/

}


