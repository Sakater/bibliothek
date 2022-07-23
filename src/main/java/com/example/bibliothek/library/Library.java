package com.example.bibliothek.library;

import com.example.bibliothek.appUser.AppUser;
import com.example.bibliothek.books.Books;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Library_borrow",
        indexes = {
                @Index(name = "user_id", columnList = "user_id"),
                @Index(name = "books_id", columnList = "books_id")
        }
)
//        indexes ={@Index(name = "books_id", columnList = "books_id")})
public class Library {

    public Library(AppUser appUser, Books book) {
        this.appUser = appUser;
        this.dateBorrowed = LocalDate.now();
        this.expiresBorrow = dateBorrowed.plusMonths(1);
        this.books = book;
    }

    @SequenceGenerator(
            name = "borrowed_books_sequence",
            sequenceName = "borrowed_books_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "borrowed_books_sequence")
    @Id
    private Long libId;

    private LocalDate dateBorrowed;
    private LocalDate expiresBorrow;
    private Integer extendedTimes;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "books_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private Books books;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "user_id"
            //foreignKey = {@ForeignKey( value = ConstraintMode.CONSTRAINT)}
    )
    private AppUser appUser;


}
