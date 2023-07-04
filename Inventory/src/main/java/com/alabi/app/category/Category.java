package com.alabi.app.category;

import com.alabi.app.brand.Brand;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(length=45, nullable=false, unique=true)
	private String name;
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
		
	public Category() {
		
	}
	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category(String name) {
		super();
		this.name = name;
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
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public static boolean isValid(Category category) {
		return category != null &&
				StringUtils.isNotBlank(category.name);
	}
	@Override
	public String toString() {
		return  name;
	}
	
}
