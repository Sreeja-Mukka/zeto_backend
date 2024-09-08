package com.example.zeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zeto.exceptions.UserNotFound;
import com.example.zeto.model.ProductOrder;
import com.example.zeto.model.User;

@Service
public class ProductOrderService {

	@Autowired
	private UserService uservice;

	public ProductOrder getProductOrderById(long uid, long id) {
		User user = uservice.getUserById(uid)
				.orElseThrow(() -> new UserNotFound("user with id: " + uid + " not found"));
		List<ProductOrder> lpo = user.getOrders();
		ProductOrder pd = null;
		for (ProductOrder po : lpo) {
			if (po.getOrderId() == id) {
				pd = po;
				break;
			}
		}
		return pd;

	}

	// create order when payment is done
}
