package com.example.zeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zeto.exceptions.ProductNotFound;
import com.example.zeto.exceptions.UserNotFound;
import com.example.zeto.model.Cart;
import com.example.zeto.model.Product;
import com.example.zeto.model.User;
import com.example.zeto.repository.CartRepository;
import com.example.zeto.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private ProductService pserv;

	@Autowired
	private UserRepository urepo;

	@Autowired
	private CartRepository crepo;

	public void SaveCartUser(Cart cart) {
		crepo.save(cart);
	}

	public Cart AddProductToCart(long userId, long productId) {

		User us = urepo.findById(userId).orElseThrow(() -> new UserNotFound("user with id: " + userId + " not found"));
		Cart cart = us.getCart();

		Product prod = pserv.getProduct(productId)
				.orElseThrow(() -> new ProductNotFound("product with id: " + productId + " not found"));
		cart.getListOfAllProducts().add(prod);
		crepo.save(cart);
		double amt = cart.getTotalAmount();
		cart.setTotalAmount(amt + prod.getPrice());
		crepo.save(cart);

		return cart;
	}

	public List<Product> getAllProductsOfUser(long userId) {
		User us = urepo.findById(userId).orElseThrow(() -> new UserNotFound("user with id: " + userId + " not found"));
		Cart cart = us.getCart();
		List<Product> pl = cart.getListOfAllProducts();
		return pl;
	}

	public double showCartAmount(long userId) {
		User us = urepo.findById(userId).orElseThrow(() -> new UserNotFound("user with id: " + userId + " not found"));
		Cart cart = us.getCart();

		return cart.getTotalAmount();
	}

}
