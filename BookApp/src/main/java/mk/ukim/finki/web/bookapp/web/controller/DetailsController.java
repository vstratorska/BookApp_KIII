package mk.ukim.finki.web.bookapp.web.controller;



import mk.ukim.finki.web.bookapp.model.Book;
import mk.ukim.finki.web.bookapp.service.Implementation.AuthorService;
import mk.ukim.finki.web.bookapp.service.Implementation.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/details")
public class DetailsController {

    private final BookService bookService;
    private final AuthorService authorService;

    public DetailsController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostMapping
    public String details(@RequestParam String isbn,
                          @RequestParam String authorId,
                          Model model) {

        bookService.addAuthorToBook(Long.valueOf(authorId), isbn);
        Book book=bookService.findBookByIsbn(isbn);
        model.addAttribute("book", book);

        return "/bookDetails";
    }
}
