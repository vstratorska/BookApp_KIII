package mk.ukim.finki.web.bookapp.service.Implementation;


import mk.ukim.finki.web.bookapp.model.Author;
import mk.ukim.finki.web.bookapp.model.Book;
import mk.ukim.finki.web.bookapp.model.BookStore;
import mk.ukim.finki.web.bookapp.model.Review;
import mk.ukim.finki.web.bookapp.repository.AuthorRepository;
import mk.ukim.finki.web.bookapp.repository.BookRepository;
import mk.ukim.finki.web.bookapp.repository.BookStoreRepository;
import mk.ukim.finki.web.bookapp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements mk.ukim.finki.web.bookapp.service.BookService {

    private final AuthorRepository authorRepository2;
    private final BookRepository bookRepository;
    private  final BookStoreRepository bookStoreRepository;

    private final ReviewRepository reviewRepository;

    public BookService(AuthorRepository authorRepository, BookRepository bookRepository, BookStoreRepository bookStoreRepository, ReviewRepository reviewRepository) {
        this.authorRepository2 = authorRepository;
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
        this.reviewRepository = reviewRepository;
    }
    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addAuthorToBook(Long authorId, String isbn) {
        Author author=authorRepository2.findById(authorId).get();
        Book book=bookRepository.findByIsbn(isbn);

        book.getAuthors().add(author);
        return this.bookRepository.save(book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Optional<Book> save(String title, String isbn, String genre, Integer year, Long id) {
        BookStore bookStore= this.bookStoreRepository.findById(id).get();
        return Optional.of(bookRepository.save(new Book(title, isbn, genre, year, bookStore)));
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteAll();
        authorRepository2.deleteAll();
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> edit(Long bookId, String title, String isbn, String genre, Integer year, Long id) {
     Book book=bookRepository.findById(bookId).get();
     BookStore bookStore=bookStoreRepository.findById(id).get();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setGenre(genre);
        book.setYear(year);
        book.setBookStore(bookStore);
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Book addReviewToBook(Review review, Book book) {
        book.getReviews().add(review);
        reviewRepository.save(review);
        return bookRepository.save(book);
    }


}
