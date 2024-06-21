package mk.ukim.finki.web.bookapp.service;



import mk.ukim.finki.web.bookapp.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {

    public List<BookStore> findAll();
    Optional<BookStore> findById(Long id);
}
