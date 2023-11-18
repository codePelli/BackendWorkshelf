package ejercicios.service;

import java.util.List;

import ejercicios.dto.Rating;

public interface IRatingService {
	
	public List<Rating> getRatings();

	public Rating RatingPerId(Long id);

	public Rating saveRating(Rating Rating);

	public Rating updateRating(Rating Rating);

	public void deleteRating(Long id);

}