package ejercicios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ejercicios.dao.IBookDAO;
import ejercicios.dao.RatingDAO;
import ejercicios.dto.Book;
import ejercicios.dto.Rating;
import ejercicios.dto.Reservation;

@Service
public class RatingServiceImpl implements IRatingService{
	
	@Autowired
	RatingDAO ratingsDAO;
	
	@Autowired
	BookServiceImpl bookService;
	
	@Autowired
	ReservationServiceImpl reservationService;

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return ratingsDAO.findAll();
	}

	@Override
	public Rating ratingPerId(Long id) {
		// TODO Auto-generated method stub
		return ratingsDAO.findById(id).get();
	}

	@Override
	public Rating saveRating(Rating id) {
		// TODO Auto-generated method stub
		return ratingsDAO.save(id);
	}

	@Override
	public Rating updateRating(Rating id) {
		// TODO Auto-generated method stub
		return ratingsDAO.save(id);
	}

	@Override
	public void deleteRating(Long id) {
		// TODO Auto-generated method stub
		ratingsDAO.deleteById(id);
	}
	
	public List<Rating> ratingsByScore(int score) {
        return ratingsDAO.findAllByScore(score);
    }

	@Override
	public Page<Rating> getPaginatedRating(Pageable pageable) {
		// TODO Auto-generated method stub
		return ratingsDAO.findAll(pageable);
	}
	
	public Page<Rating> searchRatingByScore(Integer rating, Pageable pageable) {
		// TODO Auto-generated method stub
		return ratingsDAO.findAllByScore(rating, pageable);
	}
	
	
	public List<Rating> getRatingByReservation(Reservation reservation) {
		return ratingsDAO.findByReservationId(reservation);
	}
	
	public List<Rating> getAllRatingsByBook(Long id) {
	   List<Reservation> reservations = bookService.bookPerId(id).getReservations();
	   List<Rating> allRatings = new ArrayList<>();
	   
	   for (Reservation reservation : reservations) {
		   List<Rating> reservationRatings = reservation.getRatings();
		   allRatings.addAll(reservationRatings);
	   }
	   return allRatings;
	}
}

