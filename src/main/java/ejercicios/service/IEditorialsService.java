package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ejercicios.dto.Editorials;


public interface IEditorialsService {

	List<Editorials> getEditorials();
	
	Editorials editorialPerId(Long id);
	
	Editorials saveEditorial(Editorials id);
	
	Editorials updateEditorial(Editorials id);
	
	void deleteEditorial(Long id);
}
