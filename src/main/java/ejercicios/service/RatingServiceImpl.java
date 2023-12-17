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
	
	/*public Page<Rating> getAllPaginatedRatingsByBook(Book book, Pageable pageable) {
	    List<Reservation> reservations = reservationService.getAllReservesByBook(book);
	    List<Rating> ratings = new ArrayList<>();

	    for (Reservation reservation : reservations) {
	        List<Rating> ratingsPerReservation = this.getRatingByReservation(reservation);
	        ratings.addAll(ratingsPerReservation);
	    }

	    int start = (int) pageable.getOffset();
	    int end = (start + pageable.getPageSize()) > ratings.size() ? ratings.size() : (start + pageable.getPageSize());

	    return new PageImpl<>(ratings.subList(start, end), pageable, ratings.size());
	}*/
	
    public Page<Rating> getAllPaginatedRatingsByBookId(Long bookId, Pageable pageable) {
        Book book = bookService.bookPerId(bookId);
        List<Reservation> reservations = reservationService.getAllReservesByBook(book);

        List<Rating> ratings = reservations.stream()
                .flatMap(reservation -> getRatingByReservation(reservation).stream())
                .collect(Collectors.toList());

        return new PageImpl<>(ratings, pageable, ratings.size());
    }

}

