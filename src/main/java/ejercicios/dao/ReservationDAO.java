package ejercicios.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Reservation;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation,Long>{
	List<Reservation> findAllByReturnDate(Date returnDate);
}
