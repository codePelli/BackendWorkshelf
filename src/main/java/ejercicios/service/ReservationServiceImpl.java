package ejercicios.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ejercicios.dao.ReservationDAO;
import ejercicios.dto.Book;
import ejercicios.dto.Rating;
import ejercicios.dto.Reservation;
import ejercicios.dto.User;

@Service
public class ReservationServiceImpl implements IReservationService{
	
	@Autowired
	ReservationDAO reservationsDAO;
	
	@Autowired
	BookServiceImpl bookServiceImpl;

	@Override
	public List<Reservation> getReservations() {
		// TODO Auto-generated method stub
		return reservationsDAO.findAll();
	}

	@Override
	public Reservation ReservationPerId(Long id) {
		// TODO Auto-generated method stub
		return reservationsDAO.findById(id).get();
	}

	@Override
	public Reservation saveReservation(Reservation id) {
		// TODO Auto-generated method stub
		return reservationsDAO.save(id);
	}

	@Override
	public Reservation updateReservation(Reservation id) {
		// TODO Auto-generated method stub
		return reservationsDAO.save(id);
	}

	@Override
	public void deleteReservation(Long id) {
		// TODO Auto-generated method stub
		reservationsDAO.deleteById(id);
	}
	
	public List<Reservation> reservationsByReturnDate(Date returnDate) {
        return reservationsDAO.findAllByReturnDate(returnDate);
    }

	@Override
	public Page<Reservation> getPaginatedReservation(Pageable pageable) {
		// TODO Auto-generated method stub
		return reservationsDAO.findAll(pageable);
	}
	
	@Override
	public Page<Reservation> getReservesByUser (User user, Pageable pageable) {
		// TODO Auto-generated method stub
		return reservationsDAO.findAllByUser(user, pageable);
	}

	public Page<Reservation> getReservesByBookPaginated(Book bookPerId, Pageable pageable) {
		// TODO Auto-generated method stub
		return reservationsDAO.findReservesByBook(bookPerId, pageable);
	}
}

