package mk.ukim.finki.web.bookapp.web.controller;


import mk.ukim.finki.web.bookapp.model.Book;
import mk.ukim.finki.web.bookapp.model.BookStore;
import mk.ukim.finki.web.bookapp.model.Review;
import mk.ukim.finki.web.bookapp.service.Implementation.BookService;
import mk.ukim.finki.web.bookapp.service.Implementation.BookStoreServiceImpl;
import mk.ukim.finki.web.bookapp.service.Implementation.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookStoreServiceImpl bookStoreService;
    private final ReviewService reviewService;

    public BookController(BookService bookService, BookStoreServiceImpl bookStoreService, ReviewService reviewService) {
        this.bookService = bookService;

        this.bookStoreService = bookStoreService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model)
    {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.listBooks();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
            if(this.bookService.findById(id).isPresent())
        {
            Book book = bookService.findById(id).get();
            List<BookStore> bookStores = this.bookStoreService.findAll();

            model.addAttribute("book", book);
            model.addAttribute("bookStores", bookStores);

            return "add-book";
        }
        return "redirect:/books?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model) {

        List<BookStore> bookStores = this.bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);

        return "add-book";
    }

    @GetMapping("/review-form")
    public String getAddReviewPage(Model model) {
        List<Book>books=this.bookService.listBooks();
        model.addAttribute("books", books);
        return "add-review";
    }


    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long id) {
        this.bookService.save(title, isbn, genre, year, id);
        return "redirect:/books";
    }

    @PostMapping ("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long id){
        if(bookId!=null && bookId!=0) {


            bookService.edit(bookId, title, isbn, genre, year, id);
        }
        else
        this.bookService.save(title, isbn, genre, year, id);

        return "redirect:/books";
    }

    @PostMapping ("/review")
    public String addReview(@RequestParam Long bookId,
                            @RequestParam Integer score,
                            @RequestParam String description,
                            @RequestParam("date")
                                @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date){
        Book book=this.bookService.findById(bookId).get();
        Review review=new Review(score, description, book, date);
        this.bookService.addReviewToBook(review, book);
        return "redirect:/books";
    }
    @PostMapping ("/interval")
    public String addReview(@RequestParam("from") LocalDateTime from,
                            @RequestParam("to") LocalDateTime to,
                            Model model){
        List<Review> reviews=reviewService.findByTimestampBetween(from, to);
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }


    @GetMapping("/review/{id}")
    public String Review(@PathVariable Long id, Model model) {
        List<Review> reviews=this.bookService.findById(id).get().getReviews();
        Double scores =reviews.stream().map(r->r.getScore()).collect(Collectors.averagingDouble(Integer::doubleValue));

        model.addAttribute("reviews", reviews);
        model.addAttribute("average", scores);
        return "reviews";
    }




    @GetMapping("/new/{id}")
    public String newBook(@PathVariable Long id) {
        Book book=bookService.findById(id).get();
        String newTitle= "Copy of " + book.getTitle();
        bookService.save(newTitle, book.getIsbn(), book.getGenre(), book.getYear(), book.getBookStore().getId());
        return "redirect:/books";
    }

}
