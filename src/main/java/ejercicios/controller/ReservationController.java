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
	
	@GetMapping("/byReturnDate")
    public List<Reservation> getByReturnDate(@RequestParam(name = "returnDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date returnDate) {
        return ReservationService.reservationsByReturnDate(returnDate);
    }
	
    //GET /api/proyectos/paginated?page=0&size=10
    @GetMapping("/paginated")
    public ResponseEntity<List<Reservation>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Reservation> reservationPage = ReservationService.getPaginatedReservation(PageRequest.of(page, size));
        List<Reservation> pageDTOs = reservationPage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(pageDTOs, HttpStatus.OK);
    }
}