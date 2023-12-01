package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ejercicios.dao.RatingDAO;
import ejercicios.dto.Rating;

@Service
public class RatingServiceImpl implements IRatingService{
	
	@Autowired
	RatingDAO RatingsDAO;

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return RatingsDAO.findAll();
	}

	@Override
	public Rating RatingPerId(Long id) {
		// TODO Auto-generated method stub
		return RatingsDAO.findById(id).get();
	}

	@Override
	public Rating saveRating(Rating id) {
		// TODO Auto-generated method stub
		return RatingsDAO.save(id);
	}

	@Override
	public Rating updateRating(Rating id) {
		// TODO Auto-generated method stub
		return RatingsDAO.save(id);
	}

	@Override
	public void deleteRating(Long id) {
		// TODO Auto-generated method stub
		RatingsDAO.deleteById(id);
	}
	
	public List<Rating> ratingsByScore(int score) {
        return RatingsDAO.findAllByScore(score);
    }

	@Override
	public Page<Rating> getPaginatedRating(Pageable pageable) {
		// TODO Auto-generated method stub
		return RatingsDAO.findAll(pageable);
	}
	
	public Page<Rating> searchRatingByScore(Integer rating, Pageable pageable) {
		// TODO Auto-generated method stub
		return RatingsDAO.findAllByScore(rating, pageable);
	}

}

