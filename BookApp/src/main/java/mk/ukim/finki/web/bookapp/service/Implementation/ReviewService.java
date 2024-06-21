package mk.ukim.finki.web.bookapp.service.Implementation;


import mk.ukim.finki.web.bookapp.model.Review;
import mk.ukim.finki.web.bookapp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService implements mk.ukim.finki.web.bookapp.service.ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findByTimestampBetween(LocalDateTime from, LocalDateTime to) {
        return reviewRepository.findByTimestampBetween(from, to);
    }
}