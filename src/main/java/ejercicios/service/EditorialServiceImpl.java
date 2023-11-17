package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ejercicios.dao.EditorialDAO;
import ejercicios.dto.Editorial;

public class EditorialServiceImpl implements IEditorialService{
	
	@Autowired
	EditorialDAO editorialsDAO;

	@Override
	public List<Editorial> getEditorials() {
		// TODO Auto-generated method stub
		return editorialsDAO.findAll();
	}

	@Override
	public Editorial editorialPerId(Long id) {
		// TODO Auto-generated method stub
		return editorialsDAO.findById(id).get();
	}

	@Override
	public Editorial saveEditorial(Editorial id) {
		// TODO Auto-generated method stub
		return editorialsDAO.save(id);
	}

	@Override
	public Editorial updateEditorial(Editorial id) {
		// TODO Auto-generated method stub
		return editorialsDAO.save(id);
	}

	@Override
	public void deleteEditorial(Long id) {
		// TODO Auto-generated method stub
		editorialsDAO.deleteById(id);
	}

}
