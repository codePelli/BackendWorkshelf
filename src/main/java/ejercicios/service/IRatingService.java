package ejercicios.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ejercicios.dto.Rating;

public interface IRatingService {
	
	public List<Rating> getRatings();

	public Rating RatingPerId(Long id);

	public Rating saveRating(Rating Rating);

	public Rating updateRating(Rating Rating);

	public void deleteRating(Long id);
	
	public List<Rating> ratingsByScore(int score);
	
    Page<Rating> getPaginatedRating(Pageable pageable);

}