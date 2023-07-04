//Bismillahi Rahmani Raheem
package com.alabi.app.brand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.alabi.app.category.Category;
import com.alabi.app.category.CategoryRepository;
import com.alabi.app.product.Product;


@Controller
public class BrandController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping("/allBrands/new")
	public String showNewBrandForm(Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("brand", new Brand());
		return "brand_form";
	}
	
	@GetMapping("/brands/all")
	public String listAllBrands(Model model) {
		List<Brand> listBrands = brandRepository.findAll();
		model.addAttribute("listBrands", listBrands);
		return "all_brands";
	}
	
	@PostMapping("/brand/save")
	public String saveBrand(Brand brand) {
		brandRepository.save(brand);
		return "redirect:/brands/all";
	}
	@GetMapping("/brand/edit/{id}")
	public String showEditBrandForm(@PathVariable("id") Integer id, Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		Brand brand = brandRepository.findById(id).get();
		model.addAttribute("brand", brand);
		model.addAttribute("categoryList", categoryList);
		return "brand_form";
	}
	
	@GetMapping("/brand/delete/{id}")
	public String deleteBrand(@PathVariable("id") Integer id) {
		brandRepository.deleteById(id);
		return "redirect:/brands/all";
	}
}
