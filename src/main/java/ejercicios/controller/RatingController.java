package ejercicios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import ejercicios.dto.User;
import ejercicios.service.BookServiceImpl;
import ejercicios.service.RatingServiceImpl;
import ejercicios.service.ReservationServiceImpl;
import ejercicios.service.UserServiceImpl;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingServiceImpl ratingService;
	
	@Autowired
	private ReservationServiceImpl reservationService;
	
	@Autowired
	private BookServiceImpl bookService;

	
	@Autowired
	UserServiceImpl userSerice;
	
	//FOR EVERYONE USE
	@GetMapping("/all")
	public List<Rating> getAllRatings(){
		
		return ratingService.getRatings();
	}
	
	//FOR EVERYONE USE
	@GetMapping("/detail/{id}")
	public Rating RatingPerId(@PathVariable Long id) {
		
		return ratingService.ratingPerId(id);
	}
	
	
	@PostMapping("/add")
	public Rating insertRating(@RequestBody Rating Rating) {
		Rating.setUser(getUserToken());
		return ratingService.updateRating(Rating);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Rating> updateRating(@PathVariable(name = "id") Long id, @RequestBody Rating Rating) {
		if (getUserToken().getUserId().equals(ratingService.ratingPerId(id).getUser().getUserId())) {
			Rating RatingSelected = new Rating();
			
			RatingSelected= ratingService.ratingPerId(id);
			
			RatingSelected.setComment(Rating.getComment());
			RatingSelected.setScore(Rating.getScore());
			RatingSelected.setUser(Rating.getUser());
			RatingSelected.setReservation(Rating.getReservation());
			
			RatingSelected = ratingService.updateRating(RatingSelected);
			
			return new ResponseEntity<>(RatingSelected, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	//ONLY ADMIN USE
	@DeleteMapping("/delete/{id}")
	public void deleteRating(@PathVariable Long id) {
		ratingService.deleteRating(id);
	}
	
	//FOR EVERYONE USE
	@GetMapping("/byScore/{score}")
    public List<Rating> getByScore(@PathVariable(name = "score") int score) {
        return ratingService.ratingsByScore(score);
    }
	
	//FOR EVERYONE USE
    //GET /api/proyectos/paginated?page=0&size=10
    @GetMapping("/paginated")
    public ResponseEntity<List<Rating>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Rating> ratingPage = ratingService.getPaginatedRating(PageRequest.of(page, size));
        List<Rating> pageDTOs = ratingPage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(pageDTOs, HttpStatus.OK);
    }
    
  //FOR EVERYONE USE
    // GET /rating/byScorePaginated?score=5&page=1&size=1
 	 	@GetMapping("/byScorePaginated")
 	public ResponseEntity<List<Rating>> searchByScore(@RequestParam(defaultValue = "5") int score,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

 		Page<Rating> bookPage = ratingService.searchRatingByScore(score, PageRequest.of(page, size));
 		List<Rating> bookDTOs = bookPage.getContent().stream().collect(Collectors.toList());

 		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
 	}
 	 	
 	public User getUserToken() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)auth.getPrincipal()).getUsername();
        User user = userSerice.getUser(username);
        return user;
    }
 	
 	@GetMapping("/book/{id}")
	public List<Rating> ratingsByBook(@PathVariable Long id) {
		return ratingService.getAllRatingsByBook(id);
	}
}