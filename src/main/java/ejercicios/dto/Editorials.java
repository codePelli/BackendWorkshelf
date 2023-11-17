package ejercicios.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "editorials")
public class Editorials {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "editorialName")
    private String editorialName;

	public Editorials(Long id, String editorialName) {
		super();
		this.id = id;
		this.editorialName = editorialName;
	}

	public Editorials() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEditorialName() {
		return editorialName;
	}

	public void setEditorialName(String editorialName) {
		this.editorialName = editorialName;
	}
    
}
