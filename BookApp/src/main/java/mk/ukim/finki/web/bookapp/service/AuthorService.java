package mk.ukim.finki.web.bookapp.service;



import mk.ukim.finki.web.bookapp.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author findById(Long id);
}