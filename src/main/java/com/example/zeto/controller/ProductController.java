package com.example.zeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.zeto.exceptions.CategoryNotFound;
import com.example.zeto.exceptions.ProductNotFound;
import com.example.zeto.model.Category;
import com.example.zeto.model.Product;
import com.example.zeto.service.CategoryService;
import com.example.zeto.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService ps;

	@Autowired
	CategoryService cs;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllproduct() {
		List<Product> pl = ps.getAllProductsList();
		return new ResponseEntity<>(pl, HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@RequestBody Product p) {
		Product pd = ps.saveProduct(p);
		long category_id = pd.getCatType().getCategoryId();
		Category cat = cs.searchByIdCategory(category_id)
				.orElseThrow(() -> new CategoryNotFound("category of " + category_id + " not found"));
		cat.getProductList().add(pd);
		cs.saveCategory(cat);
		return new ResponseEntity<>(pd, HttpStatus.CREATED);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		Product pd = ps.getProduct(id).orElseThrow(() -> new ProductNotFound("product not found with id " + id));
		return new ResponseEntity<>(pd, HttpStatus.OK);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product pd) {
		Product p = ps.updateProductById(id, pd);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@DeleteMapping("products/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable long id) {
		try {
			ps.deleteProductById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
