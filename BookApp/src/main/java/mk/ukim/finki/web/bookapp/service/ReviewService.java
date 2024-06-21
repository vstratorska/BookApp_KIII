package mk.ukim.finki.web.bookapp.service;



import mk.ukim.finki.web.bookapp.model.Review;

import java.time.LocalDateTime;
import java.util.List;


public interface ReviewService {
    List<Review> findByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
