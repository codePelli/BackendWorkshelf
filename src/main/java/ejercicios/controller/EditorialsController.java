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

import ejercicios.dto.Editorials;
import ejercicios.service.EditorialsServiceImpl;

@RestController
@RequestMapping("/editorial")
public class EditorialsController {

	@Autowired
	private EditorialsServiceImpl editorialService;
	
	@GetMapping
	public List<Editorials> getAllEditorials(){
		
		return editorialService.getEditorials();
	}
	
	@GetMapping("/{id}")
	public Editorials editorialPerId(@PathVariable Long id) {
		
		return editorialService.editorialPerId(id);
	}
	
	@PostMapping("/add")
	public Editorials insertEditorial(@RequestBody Editorials editorial) {
		
		return editorialService.updateEditorial(editorial);
	}
	
	@PutMapping("/{id}")
	public Editorials updateEditorial(@PathVariable(name = "id") Long id, @RequestBody Editorials editorial) {
		
		Editorials editorialSelected = new Editorials();
		
		editorialSelected.setEditorialName(editorial.getEditorialName());
		
		return editorialSelected;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEditorial(@PathVariable Long id) {
		editorialService.deleteEditorial(id);
	}
}

