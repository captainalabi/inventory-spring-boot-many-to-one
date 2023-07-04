//Bismillahi Rahmani Raheem
package com.alabi.app.product;

import java.util.ArrayList;
import java.util.List;

import com.alabi.app.category.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 128, nullable = false, unique = true)
	private String name;
	private Long price;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductDetails> details = new ArrayList<>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Product(Integer id, String name, Long price, Category category, List<ProductDetails> details) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.details = details;
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public List<ProductDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ProductDetails> details) {
		this.details = details;
	}
	public void setDetails(Integer id, String name, String value) {
		this.details.add(new ProductDetails(id, name, value, this));
	}
	public void addDetails(String name, String value) {
		this.details.add(new ProductDetails(name, value, this));
	}		
}
