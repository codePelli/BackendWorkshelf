package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ejercicios.dao.EditorialDAO;
import ejercicios.dto.Editorial;

@Service
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
	
	public Editorial editorialByName(String name) {
        return editorialsDAO.findByEditorialName(name); // Assuming findByEditorialName method exists in IEditorialDAO
    }

	@Override
	public Page<Editorial> getPaginatedEditorial(Pageable pageable) {
		// TODO Auto-generated method stub
		return editorialsDAO.findAll(pageable);
	}
	
	public Page<Editorial> searchEditorialByeditorialName(String title, Pageable pageable) {
		// TODO Auto-generated method stub
		return editorialsDAO.findAllByeditorialNameContainingIgnoreCase(title, pageable);
	}

}
