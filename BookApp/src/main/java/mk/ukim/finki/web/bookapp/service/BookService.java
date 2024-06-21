package mk.ukim.finki.web.bookapp.service;



import mk.ukim.finki.web.bookapp.model.Book;
import mk.ukim.finki.web.bookapp.model.Review;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> listBooks();
    Book addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);


    Optional<Book> save(String title, String isbn, String genre, Integer year, Long id);

    void deleteById(Long id);
    Optional<Book> findById(Long id);
    Optional<Book> edit (Long bookId, String title, String isbn, String genre, Integer year, Long Id);

   Book addReviewToBook(Review review, Book book);
}