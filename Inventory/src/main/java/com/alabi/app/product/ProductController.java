//Bismillahi Rahmani Raheem
package com.alabi.app.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alabi.app.category.Category;
import com.alabi.app.category.CategoryRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/allProducts")
	public String listAllProducts(@ModelAttribute Product product, Model model) {
		List<Product> productList = productRepository.findAll();
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("productList", productList);
		model.addAttribute("categoryList", categoryList);
		return "allproducts";
	}
	
	@GetMapping("/products/new")
	public String showNewProductForm(Model model) {	
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("product", new Product());
		model.addAttribute("categoryList", categoryList);
		
		return "new_product_form";
	}
	
	@PostMapping("/product/save")
	public ModelAndView createNewProduct(Product product, 
											RedirectAttributes redirectAttributes,
											HttpServletRequest request) {
		//handling product details
				String [] detailIDs = request.getParameterValues("detailID");
				String [] detailNames = request.getParameterValues("detailName");
				String [] detailValues = request.getParameterValues("detailValue");
				for(int i=0; i<detailNames.length; i++) {
					if(detailIDs != null && detailIDs.length > 0) {
						product.setDetails(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);
					}else {
					product.addDetails(detailNames[i], detailValues[i]);
					}
				}
		Product savedProduct = productRepository.save(product);
		
		String message = "";		
		ModelAndView mav = new ModelAndView("redirect:/products/new");
		try {
		if(savedProduct != null) {
			message = "Product " + savedProduct.getName() + " successfully created!";
		}
		}catch(DataIntegrityViolationException e) {
			message = "Duplicate entry detected! Please try again";
		}
		redirectAttributes.addFlashAttribute("message", message);
		
		return mav;
	} 
	
	@GetMapping("/product/edit/{id}")
	public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		Product product = productRepository.findById(id).get();
		model.addAttribute("product", product);
		
		model.addAttribute("categoryList", categoryList);
		
		return "new_product_form";
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		productRepository.deleteById(id);
		return "redirect:/allProducts";
	}
}
