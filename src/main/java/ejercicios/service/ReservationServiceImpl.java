package ejercicios.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ejercicios.dao.ReservationDAO;
import ejercicios.dto.Book;
import ejercicios.dto.Rating;
import ejercicios.dto.Reservation;
import ejercicios.dto.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImpl implements IReservationService {

	@Autowired
	ReservationDAO reservationsDAO;

	@Autowired
	BookServiceImpl bookServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public List<Reservation> getReservations() {
		return reservationsDAO.findAll();
	}

	@Override
	public Reservation ReservationPerId(Long id) {
		return reservationsDAO.findById(id).get();
	}

	@Override
	public Reservation saveReservation(Reservation id) {
		return reservationsDAO.save(id);
	}

	@Override
	public Reservation updateReservation(Reservation id) {
		return reservationsDAO.save(id);
	}

	@Override
	public void deleteReservation(Long id) {
		reservationsDAO.deleteById(id);
	}

	public List<Reservation> reservationsByReturnDate(Date returnDate) {
		return reservationsDAO.findAllByReturnDate(returnDate);
	}

	@Override
	public Page<Reservation> getPaginatedReservation(Pageable pageable) {
		return reservationsDAO.findAll(pageable);
	}

	@Override
	public Page<Reservation> getReservesByUser(User user, Pageable pageable) {
		return reservationsDAO.findAllByUser(user, pageable);
	}

	public Page<Reservation> getReservesByBookPaginated(Book bookPerId, Pageable pageable) {
		return reservationsDAO.findReservesByBook(bookPerId, pageable);
	}

	public Page<Reservation> getReservesByUserId(User userId, Pageable pageable) {
		return reservationsDAO.findReservesByUser(userId, pageable);
	}

	 public Page<Reservation> getMyBooksReservations(User userId, Pageable pageable) {
        List<Book> books = bookServiceImpl.getBookListByUserId(userId.getUserId());
        List<Reservation> allReservations = new ArrayList<>();

        for (Book book : books) {
            Page<Reservation> reservationsPerBook = getReservesByBookPaginated(book, pageable);
            allReservations.addAll(reservationsPerBook.getContent());
        }
        
        return new PageImpl<>(allReservations, pageable, allReservations.size());
    }
	 
	 public Reservation addReservation(User user, Book book) {
		 if(book.getReserved() == 0) {
			Reservation reservation = new Reservation();
			Integer reservationDays = book.getReservationDuration();
			Date reservationStart = getCurrentDateTime();
			Date returnDate = addDays(reservationStart, reservationDays);
			
				book.setReserved(1);
			
			reservation.setRequestDate(reservationStart);
			reservation.setReturnDate(returnDate);
			reservation.setUser(user);
			reservation.setBook(book);
			
			List<Reservation> reservations = book.getReservations();
			reservations.add(reservation);
			book.setReservations(reservations);
			bookServiceImpl.updateBook(book);
			
			return saveReservation(reservation);
		 }
		 return null;
	 }
	 
	 private static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
	 
	 public static Date getCurrentDateTime() {
	 	Date fecha = new Date();
        long unaHoraEnMilisegundos = 3600000;
        return new Date(fecha.getTime() + unaHoraEnMilisegundos);
    }
}
