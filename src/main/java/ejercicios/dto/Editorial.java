package ejercicios.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "editorials")
public class Editorial {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "editorialName")
    private String editorialName;
    
    
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "editorial", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books;


	public Editorial() {
		super();
	}

	public Editorial(Long id, String editorialName, List<Book> books) {
		super();
		this.id = id;
		this.editorialName = editorialName;
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
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
