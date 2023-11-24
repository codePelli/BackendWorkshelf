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

import ejercicios.dto.Rating;
import ejercicios.service.RatingServiceImpl;

@RestController
@RequestMapping("/Rating")
public class RatingController {

	@Autowired
	private RatingServiceImpl RatingService;
	
	@GetMapping
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
}