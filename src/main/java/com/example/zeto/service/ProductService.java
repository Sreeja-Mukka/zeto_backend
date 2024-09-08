package com.example.zeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zeto.exceptions.ProductNotFound;
import com.example.zeto.model.Product;
import com.example.zeto.repository.CategoryRepository;
import com.example.zeto.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository prepo;

	@Autowired
	CategoryRepository crepo;

	public List<Product> getAllProductsList() {
		return prepo.findAll();
	}

	public Product saveProduct(Product p) {
		return prepo.save(p);
	}

	public Optional<Product> getProduct(long id) {
		return prepo.findById(id);
	}

	public Product updateProductById(long id, Product pd) {

		Product p = prepo.findById(id).orElseThrow(() -> new ProductNotFound("Product with id: " + id + " not found"));
		p.setNameOfProduct(pd.getNameOfProduct());
		p.setDescription(pd.getDescription());
		p.setPrice(pd.getPrice());
		p.setStockAvailable(pd.getStockAvailable());

		return prepo.save(p);
	}

	public void deleteProductById(long id) {
		Product p = prepo.findById(id).orElseThrow(() -> new ProductNotFound("Product with id: " + id + " not found"));
		prepo.delete(p);
	}

}
