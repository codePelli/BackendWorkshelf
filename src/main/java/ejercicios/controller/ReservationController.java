package ejercicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ejercicios.dto.Reservation;
import ejercicios.service.ReservationServiceImpl;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationServiceImpl ReservationService;
	
	@GetMapping
	public List<Reservation> getAllReservations(){
		
		return ReservationService.getReservations();
	}
	
	@GetMapping("/{id}")
	public Reservation ReservationPerId(@PathVariable Long id) {
		
		return ReservationService.ReservationPerId(id);
	}
	
	@PostMapping("/add")
	public Reservation insertReservation(@RequestBody Reservation Reservation) {
		
		return ReservationService.updateReservation(Reservation);
	}
	
	@PutMapping("/{id}")
	public Reservation updateReservation(@PathVariable(name = "id") Long id, @RequestBody Reservation Reservation) {
		
		Reservation ReservationSelected = new Reservation();
		
		ReservationSelected= ReservationService.ReservationPerId(id);
		
		ReservationSelected.setRequestDate(Reservation.getRequestDate());
		ReservationSelected.setReturnDate(Reservation.getReturnDate());
		ReservationSelected.setUser(Reservation.getUser());
		ReservationSelected.setBook(Reservation.getBook());
		
		ReservationSelected = ReservationService.updateReservation(ReservationSelected);
		
		return ReservationSelected;
	}
	
	@DeleteMapping("/{id}")
	public void deleteReservation(@PathVariable Long id) {
		ReservationService.deleteReservation(id);
	}
}