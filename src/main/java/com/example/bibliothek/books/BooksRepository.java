package com.example.bibliothek.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface BooksRepository extends
        JpaRepository<Books, Long> {

    @Transactional
    @Query(value="SELECT b FROM Books b " +
            "WHERE b.title= :title " +
            "AND b.author= :author")
    List<Books> findByNameAndAuthor(@Param("title") String title,
                             @Param("author") String author);

    @Transactional
    @Query(value="SELECT b FROM Books b " +
            "WHERE b.title= :search " +
            "OR b.author= :search " +
            "OR b.about= :search " +
            "OR b.category= :search " +
            "OR b.language= :search")
    List<Books> findBySingleEntry(@Param("search") String search);


}
