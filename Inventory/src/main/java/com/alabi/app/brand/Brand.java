package com.alabi.app.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alabi.app.category.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=128, nullable = false, unique = true)
	private String name;
	@OneToMany
	@JoinColumn(name="brand_id")
	List<Category> categories = new ArrayList<Category>();
	
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Brand(Integer id, String name, List<Category> categories) {
		super();
		this.id = id;
		this.name = name;
		this.categories = categories;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
}
