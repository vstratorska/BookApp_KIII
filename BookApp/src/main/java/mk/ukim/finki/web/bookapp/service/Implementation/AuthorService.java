package mk.ukim.finki.web.bookapp.service.Implementation;


import mk.ukim.finki.web.bookapp.model.Author;
import mk.ukim.finki.web.bookapp.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements mk.ukim.finki.web.bookapp.service.AuthorService {

    private final AuthorRepository authorRepository1;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository1 = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return this.authorRepository1.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository1.findById(id).get();
    }
}
