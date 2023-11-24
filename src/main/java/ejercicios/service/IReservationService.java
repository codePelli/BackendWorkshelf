package ejercicios.service;

import java.util.List;

import ejercicios.dto.Reservation;

public interface IReservationService {
	
	public List<Reservation> getReservations();

	public Reservation ReservationPerId(Long id);

	public Reservation saveReservation(Reservation Reservation);

	public Reservation updateReservation(Reservation Reservation);

	public void deleteReservation(Long id);

}
