package ejercicios.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ejercicios.dao.ReservationDAO;
import ejercicios.dto.Reservation;

@Service
public class ReservationServiceImpl implements IReservationService{
	
	@Autowired
	ReservationDAO ReservationsDAO;

	@Override
	public List<Reservation> getReservations() {
		// TODO Auto-generated method stub
		return ReservationsDAO.findAll();
	}

	@Override
	public Reservation ReservationPerId(Long id) {
		// TODO Auto-generated method stub
		return ReservationsDAO.findById(id).get();
	}

	@Override
	public Reservation saveReservation(Reservation id) {
		// TODO Auto-generated method stub
		return ReservationsDAO.save(id);
	}

	@Override
	public Reservation updateReservation(Reservation id) {
		// TODO Auto-generated method stub
		return ReservationsDAO.save(id);
	}

	@Override
	public void deleteReservation(Long id) {
		// TODO Auto-generated method stub
		ReservationsDAO.deleteById(id);
	}
	
	public List<Reservation> reservationsByReturnDate(Date returnDate) {
        return ReservationsDAO.findAllByReturnDate(returnDate);
    }

	@Override
	public Page<Reservation> getPaginatedReservation(Pageable pageable) {
		// TODO Auto-generated method stub
		return ReservationsDAO.findAll(pageable);
	}

}

