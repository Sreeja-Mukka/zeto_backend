package com.example.zeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.zeto.model.ProductOrder;
import com.example.zeto.service.ProductOrderService;

@RestController
public class ProductOrderController {

	@Autowired
	private ProductOrderService pos;

	@GetMapping("/users/{uid}/orders/{oid}")
	public ResponseEntity<ProductOrder> getOrderById(@PathVariable long uid, @PathVariable long oid) {
		ProductOrder pod = pos.getProductOrderById(uid, oid);
		return new ResponseEntity<>(pod, HttpStatus.OK);
	}

	// create a order from the payments table
}
