package ejercicios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ejercicios.dto.Reservation;

public interface ReservationDAO extends JpaRepository<Reservation,Long>{

}
