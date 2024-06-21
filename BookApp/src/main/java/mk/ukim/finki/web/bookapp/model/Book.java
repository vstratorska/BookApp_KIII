package mk.ukim.finki.web.bookapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Book {

    private String isbn;
    private String title;
    private String genre;
    private int year;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Author> authors;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BookStore bookStore;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Review> reviews;

    public Book() {
    }

    public Book(String isbn, String title, String genre, int year, List<Author> authors, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore=bookStore;
    }

    public Book(String title, String isbn, String genre, int year, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = null;
        this.bookStore=bookStore;
    }
}
