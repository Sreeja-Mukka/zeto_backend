package com.example.zeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.zeto.exceptions.CategoryNotFound;
import com.example.zeto.model.Category;
import com.example.zeto.model.Product;
import com.example.zeto.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService cs;

	@GetMapping("/categories/{id}/products")
	public ResponseEntity<List<Product>> getProductListOfCategory(@PathVariable long id) {
		List<Product> pl = cs.getAllProductsOfCategory(id);
		return new ResponseEntity<>(pl, HttpStatus.OK);
	}

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> cl = cs.getAllCategories();
		return new ResponseEntity<>(cl, HttpStatus.OK);
	}

	@PostMapping("/categories")
	public ResponseEntity<Category> save(@RequestBody Category c) {
		Category cat = cs.saveCategory(c);
		return new ResponseEntity<>(cat, HttpStatus.CREATED);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
		Category cat = cs.searchByIdCategory(id).orElseThrow(() -> new CategoryNotFound("Category with id : " + id));
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

}
