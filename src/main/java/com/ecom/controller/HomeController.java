package com.ecom.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.entity.Product;
import com.ecom.entity.UserDetail;
import com.ecom.service.CategoryService;
import com.ecom.service.ProductService;
import com.ecom.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	@GetMapping("/welcome")
	public String welcomePage() {
		return "welcome";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/products")
	public String products(Model m, @RequestParam(value = "category", defaultValue = "") String category) {
		m.addAttribute("activeCategories", categoryService.getAllActiveCategory());
		m.addAttribute("activeProducts", productService.getAllActiveProducts(category));
		m.addAttribute("paramValue", category);

		return "products";
	}

	@GetMapping("/product/{id}")
	public String product(@PathVariable int id, Model m) {
		Product product = productService.getProductById(id);
		m.addAttribute("product", product);
		return "view_product";
	}

	@PostMapping("/saveUser") /* @RequestParam("image") MultipartFile file, */
	public String saveUser(@ModelAttribute UserDetail userDetail, HttpSession session) throws IOException {

		UserDetail saveUser = userService.saveUser(userDetail);
		userService.saveUser(userDetail);
		if (!ObjectUtils.isEmpty(saveUser)) {
			session.setAttribute("sucMsg", "User registration successfully!");
		} else {
			session.setAttribute("errMsg", "Something went wrong");
		}
		return "redirect:/register";
	}

}
