package ejercicios.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ejercicios.dto.Reservation;

public interface ReservationDAO extends JpaRepository<Reservation,Long>{
	List<Reservation> findAllByReturnDate(Date returnDate);
}
