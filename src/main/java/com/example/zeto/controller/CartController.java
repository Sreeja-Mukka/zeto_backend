package com.example.zeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zeto.model.Cart;
import com.example.zeto.model.Product;
import com.example.zeto.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cserv;

	@PostMapping("/user/{uid}/add/{pid}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable long uid, @PathVariable long pid) {
		Cart cart = cserv.AddProductToCart(uid, pid);
		System.out.println(cart);
		return new ResponseEntity<>(cart, HttpStatus.CREATED);
	}

	@GetMapping("/user/{uid}/cart")
	public ResponseEntity<List<Product>> showListOfProductsInCart(@PathVariable long uid) {
		List<Product> pl = cserv.getAllProductsOfUser(uid);
		return new ResponseEntity<>(pl, HttpStatus.OK);
	}

	@GetMapping("/user/{uid}/checkout")
	public ResponseEntity<Double> showCartAmount(@PathVariable long uid) {
		double amount = cserv.showCartAmount(uid);
		return new ResponseEntity<>(amount, HttpStatus.OK);
	}
}
