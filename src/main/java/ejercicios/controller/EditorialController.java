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

import ejercicios.dto.Editorial;
import ejercicios.service.EditorialServiceImpl;

@RestController
@RequestMapping("/editorial")
public class EditorialController {

	@Autowired
	private EditorialServiceImpl editorialService;
	
	@GetMapping()
	public List<Editorial> getAllEditorials(){
		
		return editorialService.getEditorials();
	}
	
	@GetMapping("/{id}")
	public Editorial editorialPerId(@PathVariable Long id) {
		
		return editorialService.editorialPerId(id);
	}
	
	@PostMapping("/add")
	public Editorial insertEditorial(@RequestBody Editorial editorial) {
		
		return editorialService.updateEditorial(editorial);
	}
	
	@PutMapping("/{id}")
	public Editorial updateEditorial(@PathVariable(name = "id") Long id, @RequestBody Editorial editorial) {
		
		Editorial editorialSelected = new Editorial();
		
		editorialSelected.setEditorialName(editorial.getEditorialName());
		
		return editorialSelected;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEditorial(@PathVariable Long id) {
		editorialService.deleteEditorial(id);
	}
	
	@GetMapping("/byName/{name}")
    public Editorial getByName(@PathVariable(name = "name") String name) {
        return editorialService.editorialByName(name);
    }
	
    //GET /book/paginated?page=0&size=3
    @GetMapping("/paginated")
    public ResponseEntity<List<Editorial>> getPaginatedProyectos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Editorial> editorialPage = editorialService.getPaginatedEditorial(PageRequest.of(page, size));
        List<Editorial> userDTOs = editorialPage.getContent().stream().collect(Collectors.toList());

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
    
    // GET /book/byEditorialName?title=editorial&page=1&size=1
 	@GetMapping("/byEditorialName")
 	public ResponseEntity<List<Editorial>> searchByeditorialName(@RequestParam(name = "editorial") String title,
 			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

 		Page<Editorial> bookPage = editorialService.searchEditorialByeditorialName(title, PageRequest.of(page, size));
 		List<Editorial> bookDTOs = bookPage.getContent().stream().collect(Collectors.toList());

 		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
 	}
}

