package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ejercicios.dao.RatingDAO;
import ejercicios.dto.Rating;

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

}
