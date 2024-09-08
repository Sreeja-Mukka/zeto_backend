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

import com.example.zeto.exceptions.UserNotFound;
import com.example.zeto.model.ProductOrder;
import com.example.zeto.model.User;
import com.example.zeto.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userv;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getListOfUsers() {
		List<User> ul = userv.getAllUsers();
		return new ResponseEntity<>(ul, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<User> createNewUser(@RequestBody User u) {
		User user = userv.saveUser(u);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> findUserById(@PathVariable long id) {
		User u = userv.getUserById(id).orElseThrow(() -> new UserNotFound("User with Id " + id + " not found"));
		return new ResponseEntity<>(u, HttpStatus.OK);
	}

	@GetMapping("users/name/{name}")
	public ResponseEntity<User> findUserByName(@PathVariable String name) {
		User u = userv.getUserByName(name).orElseThrow(() -> new UserNotFound("User with Name " + name + " not found"));
		return new ResponseEntity<>(u, HttpStatus.OK);
	}

	@GetMapping("users/{id}/orders")
	public ResponseEntity<List<ProductOrder>> getListOfOrders(@PathVariable long id) {
		List<ProductOrder> pol = userv.getListOfAllProductOrdersOfUser(id);
		return new ResponseEntity<>(pol, HttpStatus.OK);
	}
}
