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
import ejercicios.service.RatingServiceImpl;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingServiceImpl RatingService;
	
	@GetMapping()
	public List<Rating> getAllRatings(){
		
		return RatingService.getRatings();
	}
	
	@GetMapping("/{id}")
	public Rating RatingPerId(@PathVariable Long id) {
		
		return RatingService.RatingPerId(id);
	}
	
	@PostMapping("/add")
	public Rating insertRating(@RequestBody Rating Rating) {
		
		return RatingService.updateRating(Rating);
	}
	
	@PutMapping("/{id}")
	public Rating updateRating(@PathVariable(name = "id") Long id, @RequestBody Rating Rating) {
		
		Rating RatingSelected = new Rating();
		
		RatingSelected= RatingService.RatingPerId(id);
		
		RatingSelected.setComment(Rating.getComment());
		RatingSelected.setScore(Rating.getScore());
		RatingSelected.setUser(Rating.getUser());
		RatingSelected.setReservation(Rating.getReservation());
		
		RatingSelected = RatingService.updateRating(RatingSelected);
		
		return RatingSelected;
	}
	
	@DeleteMapping("/{id}")
	public void deleteRating(@PathVariable Long id) {
		RatingService.deleteRating(id);
	}
	
	@GetMapping("/byScore/{score}")
    public List<Rating> getByScore(@PathVariable(name = "score") int score) {
        return RatingService.ratingsByScore(score);
    }
	
    //GET /api/proyectos/paginated?page=0&size=10
    @GetMapping("/paginated")
    public ResponseEntity<List<Rating>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Rating> ratingPage = RatingService.getPaginatedRating(PageRequest.of(page, size));
        List<Rating> pageDTOs = ratingPage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(pageDTOs, HttpStatus.OK);
    }
    
    // GET /rating/byScorePaginated?score=5&page=1&size=1
 	 	@GetMapping("/byScorePaginated")
 	public ResponseEntity<List<Rating>> searchByScore(@RequestParam(defaultValue = "5") int score,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

 		Page<Rating> bookPage = RatingService.searchRatingByScore(score, PageRequest.of(page, size));
 		List<Rating> bookDTOs = bookPage.getContent().stream().collect(Collectors.toList());

 		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
 	}
}