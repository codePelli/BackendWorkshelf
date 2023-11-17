package ejercicios.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "editorials")
public class Editorials {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "editorialName")
    private String editorialName;
    
    @OneToMany(mappedBy = "editorials")
    private List<Users> users;

	public Editorials() {
		super();
	}

	public Editorials(Long id, String editorialName, List<Users> users) {
		super();
		this.id = id;
		this.editorialName = editorialName;
		this.users = users;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
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
