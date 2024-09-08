package com.example.zeto.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class ProductOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;

	private double amount;
	private LocalDateTime orderDate;
	// private List<Product> productList;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public ProductOrder() {
	}

	public ProductOrder(long orderId, LocalDateTime orderDate) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;

	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	/*
	 * public List<Product> getProductList() { return productList; }
	 * 
	 * public void setProductList(List<Product> productList) { this.productList =
	 * productList; }
	 */

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
