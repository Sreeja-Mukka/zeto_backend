package com.example.zeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zeto.exceptions.CategoryNotFound;
import com.example.zeto.model.Category;
import com.example.zeto.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.zeto.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository crepo;
	
	public List<Product> getAllProductsOfCategory(long id){
		Category cat = crepo.findById(id).orElseThrow(()-> new CategoryNotFound("category of "+id + " not found"));
		return cat.getProductList();
	}
	
	public List<Category> getAllCategories(){
		return crepo.findAll();
	}
	
	public Category saveCategory(Category c) {
		return crepo.save(c);
	}
	
	public Optional<Category> searchByIdCategory(long id){
		return crepo.findById(id);
	}
}
