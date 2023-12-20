package ejercicios.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ejercicios.dto.Reservation;
import ejercicios.dto.User;

public interface IReservationService {
	
	public List<Reservation> getReservations();

	public Reservation ReservationPerId(Long id);

	public Reservation saveReservation(Reservation Reservation);

	public Reservation updateReservation(Reservation Reservation);

	public void deleteReservation(Long id);
	
	public List<Reservation> reservationsByReturnDate(Date returnDate);
	
    Page<Reservation> getPaginatedReservation(Pageable pageable);

	Page<Reservation> getReservesByUser(User user, Pageable pageable);
	
	Reservation getReservationByUserAndBook(Long userId, Long bookId);

}
