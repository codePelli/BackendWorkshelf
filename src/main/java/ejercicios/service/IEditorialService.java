package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ejercicios.dto.Editorial;

public interface IEditorialService {

	List<Editorial> getEditorials();
	
	Editorial editorialPerId(Long id);
	
	Editorial saveEditorial(Editorial id);
	
	Editorial updateEditorial(Editorial id);
	
	void deleteEditorial(Long id);
}
