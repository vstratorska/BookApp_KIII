package mk.ukim.finki.web.bookapp.repository;


import mk.ukim.finki.web.bookapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
