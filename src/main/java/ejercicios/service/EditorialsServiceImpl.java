package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ejercicios.dao.EditorialsDAO;
import ejercicios.dto.Editorials;

public class EditorialsServiceImpl implements IEditorialsService{
	
	@Autowired
	EditorialsDAO editorialsDAO;

	@Override
	public List<Editorials> getEditorials() {
		// TODO Auto-generated method stub
		return editorialsDAO.findAll();
	}

	@Override
	public Editorials editorialPerId(Long id) {
		// TODO Auto-generated method stub
		return editorialsDAO.findById(id).get();
	}

	@Override
	public Editorials saveEditorial(Editorials id) {
		// TODO Auto-generated method stub
		return editorialsDAO.save(id);
	}

	@Override
	public Editorials updateEditorial(Editorials id) {
		// TODO Auto-generated method stub
		return editorialsDAO.save(id);
	}

	@Override
	public void deleteEditorial(Long id) {
		// TODO Auto-generated method stub
		editorialsDAO.deleteById(id);
	}

}
