package ejercicios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ejercicios.dto.Book;
import ejercicios.dto.Rating;
import ejercicios.dto.Reservation;
import ejercicios.dto.User;
import ejercicios.service.BookServiceImpl;
import ejercicios.service.ReservationServiceImpl;
import ejercicios.service.UserServiceImpl;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationServiceImpl reservationService;
	
	@Autowired
	BookServiceImpl bookService;
	
	@Autowired
	UserServiceImpl userSerice;
		
	//ONLY ADMIN USE
	@GetMapping("/all")
	public List<Reservation> getAllReservations(){
		
		return reservationService.getReservations();
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Reservation> ReservationPerId(@PathVariable Long id) {
		if (getUserToken().getUserId().equals(reservationService.ReservationPerId(id).getUser().getUserId())) {
			
			return new ResponseEntity<>(reservationService.ReservationPerId(id), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	//ONLY REGISTERED USER
	@PostMapping("/add")
	public Reservation insertReservation(@RequestBody Reservation Reservation) {
		Reservation.setUser(getUserToken());
		bookService.bookPerId(Reservation.getBook().getId()).setReserved(1);
		bookService.updateBook(bookService.bookPerId(Reservation.getBook().getId()));
		return reservationService.updateReservation(Reservation);
	}
	
	//ONLY REGISTERED USER
	@PutMapping("/update/{id}")
	public ResponseEntity<Reservation> updateReservation(@PathVariable(name = "id") Long id, @RequestBody Reservation Reservation) {
		
		if (getUserToken().getUserId().equals(reservationService.ReservationPerId(id).getUser().getUserId())) {
			Reservation reservationSelected = new Reservation();
			
			reservationSelected= reservationService.ReservationPerId(id);
			Reservation.getBook().setReserved(0);
			bookService.updateBook(Reservation.getBook());
			reservationSelected.setBook(Reservation.getBook());
			
			reservationSelected = reservationService.updateReservation(reservationSelected);
			
			return new ResponseEntity<>(reservationSelected, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	//ONLY ADMIN USE
	@DeleteMapping("/delete/{id}")
	public void deleteReservation(@PathVariable Long id) {
		reservationService.deleteReservation(id);
	}
	
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
    
	@GetMapping("/reserveByBookId/{id}")
	public ResponseEntity<List<Reservation>> listByBookId(
			@PathVariable Long id,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<Reservation> resvId = reservationService.getReservesByBookPaginated(bookService.bookPerId(id), PageRequest.of(page, size));
		List<Reservation> pageId = resvId.getContent().stream().collect(Collectors.toList());

		return new ResponseEntity<>(pageId, HttpStatus.OK);
	}
	
	@GetMapping("/reserveByUserId")
	public ResponseEntity<List<Reservation>> reserveByUser(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<Reservation> resvId = reservationService.getReservesByUserId(getUserToken(), PageRequest.of(page, size));
		List<Reservation> pageId = resvId.getContent().stream().collect(Collectors.toList());

		return new ResponseEntity<>(pageId, HttpStatus.OK);
	}
    
    public User getUserToken() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)auth.getPrincipal()).getUsername();
        User user = userSerice.getUser(username);
        return user;
    }
}