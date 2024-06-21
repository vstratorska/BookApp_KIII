package mk.ukim.finki.web.bookapp.web.controller;


import mk.ukim.finki.web.bookapp.service.Implementation.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public String AuthorsPage(@RequestParam String bookIsbn, Model model)
    {
        model.addAttribute("authors", authorService.listAuthors());
        model.addAttribute("isbn", bookIsbn);

        return "authorList";
    }


}
