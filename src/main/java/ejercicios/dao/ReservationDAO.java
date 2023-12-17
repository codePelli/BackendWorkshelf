package ejercicios.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Reservation;
import ejercicios.dto.User;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation,Long>{
	List<Reservation> findAllByReturnDate(Date returnDate);
	Reservation findById(int id);
	
	<T> Page<T> findAllByUser(User user, Pageable pageable);

}
