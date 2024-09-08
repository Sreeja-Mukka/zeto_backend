package com.example.zeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zeto.exceptions.UserNotFound;
import com.example.zeto.model.Cart;
import com.example.zeto.model.ProductOrder;
import com.example.zeto.model.User;
import com.example.zeto.repository.CartRepository;
import com.example.zeto.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository urepo;

	@Autowired
	private CartRepository crepo;

	public List<User> getAllUsers() {
		return urepo.findAll();
	}

	public Optional<User> getUserById(long id) {
		return urepo.findById(id);
	}

	public Optional<User> getUserByName(String name) {
		return urepo.findByUname(name);
	}

	public List<ProductOrder> getListOfAllProductOrdersOfUser(long id) {
		User us = urepo.findById(id).orElseThrow(() -> new UserNotFound("user with id: " + id + " not found"));
		return us.getOrders();
	}

	public User saveUser(User u) {
		Cart cart = new Cart(u);
		urepo.save(u);
		crepo.save(cart);

		return urepo.save(u);
	}
}
