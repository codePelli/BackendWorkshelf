package ejercicios.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ejercicios.dto.Reservation;
import ejercicios.security.LibraryUserDetails;
import ejercicios.service.ReservationServiceImpl;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationServiceImpl reservationService;
	
	//For getting user token
	private LibraryUserDetails getToken;
		
	//ONLY ADMIN USE
	@GetMapping
	public List<Reservation> getAllReservations(){
		
		return reservationService.getReservations();
	}
	
	@GetMapping("/{id}")
	public Reservation ReservationPerId(@PathVariable Long id) {
		if (getToken.getUserToken().getUserId() == reservationService.ReservationPerId(id).getUser().getUserId()) {
			return reservationService.ReservationPerId(id);
		}
		else {
			return null;
		}
	}
	
	
	@PostMapping("/add")
	public Reservation insertReservation(@RequestBody Reservation Reservation) {
		if (getToken.getUserToken().getUserId() == Reservation.getUser().getUserId()) {
			return reservationService.updateReservation(Reservation);
		}
		else {
			return null;
		}
	}
	
	@PutMapping("/{id}")
	public Reservation updateReservation(@PathVariable(name = "id") Long id, @RequestBody Reservation Reservation) {
		
		if (getToken.getUserToken().getUserId() == Reservation.getUser().getUserId()) {
			Reservation reservationSelected = new Reservation();
			
			reservationSelected= reservationService.ReservationPerId(id);
			
			reservationSelected.setRequestDate(Reservation.getRequestDate());
			reservationSelected.setReturnDate(Reservation.getReturnDate());
			reservationSelected.setUser(Reservation.getUser());
			reservationSelected.setBook(Reservation.getBook());
			
			reservationSelected = reservationService.updateReservation(reservationSelected);
			
			return reservationSelected;
		}
		else {
			return null;
		}
		
	}
	
	//ONLY ADMIN USE
	@DeleteMapping("/{id}")
	public void deleteReservation(@PathVariable Long id) {
		reservationService.deleteReservation(id);
	}
	
	/**
	@GetMapping("/byReturnDate")
    public List<Reservation> getByReturnDate(@RequestParam(name = "returnDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date returnDate) {
        
		return reservationService.reservationsByReturnDate(returnDate);
    }
    **/
	
	//ONLY ADMIN USE
    //GET /api/proyectos/paginated?page=0&size=10
    @GetMapping("/paginated")
    public ResponseEntity<List<Reservation>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Reservation> reservationPage = reservationService.getPaginatedReservation(PageRequest.of(page, size));
        List<Reservation> pageDTOs = reservationPage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(pageDTOs, HttpStatus.OK);
    }
}