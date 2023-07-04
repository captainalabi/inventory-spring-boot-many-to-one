package com.alabi.app.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {

	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping("/categories")
	public String listCategory(Model model) {
		
		List<Category> listCategories = categoryRepository.findAll();
		
		model.addAttribute("category", listCategories);
		return "all_categories";
	}
	
	@GetMapping("/categories/new")
	public String showNewCategoryForm(Model model) {
		
		model.addAttribute("category", new Category());
		return "new_category_form";
	}
	
	@PostMapping("/createNewCategory")
	public ModelAndView createNewCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
		
		ModelAndView mav = new ModelAndView("redirect:/categories/new");
		String message = "";		
		if(Category.isValid(category)) {
			try {
				categoryRepository.save(category);
				message = "Category Successfully created!";
			}catch(DataIntegrityViolationException e) {
				message = "Error! Duplicate Entry";
			}		
		}else {
			message = "Error! Duplicate Entry";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
}
