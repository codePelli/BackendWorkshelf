package ejercicios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import ejercicios.dto.Rating;
import ejercicios.security.LibraryUserDetails;
import ejercicios.service.RatingServiceImpl;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingServiceImpl RatingService;
	
	//For getting user token
	private LibraryUserDetails getToken;
	
	//FOR EVERYONE USE
	@GetMapping()
	public List<Rating> getAllRatings(){
		
		return RatingService.getRatings();
	}
	
	//FOR EVERYONE USE
	@GetMapping("/{id}")
	public Rating RatingPerId(@PathVariable Long id) {
		
		return RatingService.RatingPerId(id);
	}
	
	
	@PostMapping("/add")
	public Rating insertRating(@RequestBody Rating Rating) {
		if (getToken.getUserToken().getUserId() == Rating.getUser().getUserId()) {
			return RatingService.updateRating(Rating);
		}
		else {
			return null;
		}
		
	}
	
	
	@PutMapping("/{id}")
	public Rating updateRating(@PathVariable(name = "id") Long id, @RequestBody Rating Rating) {
		if (getToken.getUserToken().getUserId() == Rating.getUser().getUserId()) {
			Rating RatingSelected = new Rating();
			
			RatingSelected= RatingService.RatingPerId(id);
			
			RatingSelected.setComment(Rating.getComment());
			RatingSelected.setScore(Rating.getScore());
			RatingSelected.setUser(Rating.getUser());
			RatingSelected.setReservation(Rating.getReservation());
			
			RatingSelected = RatingService.updateRating(RatingSelected);
			
			return RatingSelected;
		}
		else {
			return null;
		}
		
	}
	
	//ONLY ADMIN USE
	@DeleteMapping("/{id}")
	public void deleteRating(@PathVariable Long id) {
		RatingService.deleteRating(id);
	}
	
	//FOR EVERYONE USE
	@GetMapping("/byScore/{score}")
    public List<Rating> getByScore(@PathVariable(name = "score") int score) {
        return RatingService.ratingsByScore(score);
    }
	
	//FOR EVERYONE USE
    //GET /api/proyectos/paginated?page=0&size=10
    @GetMapping("/paginated")
    public ResponseEntity<List<Rating>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Rating> ratingPage = RatingService.getPaginatedRating(PageRequest.of(page, size));
        List<Rating> pageDTOs = ratingPage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(pageDTOs, HttpStatus.OK);
    }
    
  //FOR EVERYONE USE
    // GET /rating/byScorePaginated?score=5&page=1&size=1
 	 	@GetMapping("/byScorePaginated")
 	public ResponseEntity<List<Rating>> searchByScore(@RequestParam(defaultValue = "5") int score,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

 		Page<Rating> bookPage = RatingService.searchRatingByScore(score, PageRequest.of(page, size));
 		List<Rating> bookDTOs = bookPage.getContent().stream().collect(Collectors.toList());

 		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
 	}
}