package com.example.bibliothek.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface BooksRepository extends
        JpaRepository<Books, Long> {

    @Transactional
    @Query("SELECT new com.example.bibliothek.books.BooksRequest(" +
            "b.title, b.author, " +
            "b.about, b.category, " +
            "b.language, b.isbn, " +
            "b.published, b.quantity) FROM Books b " +
            "WHERE b.title LIKE %:title%")
    List<BooksRequest> findByNameContaining(@Param("title") String title);

    @Transactional
    @Query("SELECT b FROM Books b " +
            "WHERE b.title LIKE %:search% " +
            "OR b.author LIKE %:search% " +
            "OR b.about LIKE %:search% " +
            "OR b.category LIKE %:search% " +
            "OR b.language LIKE %:search% " +
            "OR b.isbn LIKE %:search%")
    List<BooksRequest> findBySingleEntry(@Param("search") String search);

    Books findBooksByIsbn(Long isbn);


}
