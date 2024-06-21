package mk.ukim.finki.web.bookapp.repository;


import mk.ukim.finki.web.bookapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
