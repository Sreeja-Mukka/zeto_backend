package com.example.zeto.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private User user;

	private double totalAmount;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> listOfAllProducts;

	public Cart() {
	}

	public Cart(User user) {
		super();
		this.user = user;
		this.listOfAllProducts = new ArrayList<Product>();
	}

	public Cart(long cartId, List<Product> listOfAllProducts) {
		super();
		this.cartId = cartId;
		this.listOfAllProducts = listOfAllProducts;
	}

	public List<Product> getListOfAllProducts() {
		return listOfAllProducts;
	}

	public void setProductToList(Product prod) {
		this.listOfAllProducts.add(prod);
	}

	public void setListOfAllProducts(List<Product> listOfAllProducts) {
		this.listOfAllProducts = listOfAllProducts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
