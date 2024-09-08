package com.example.zeto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nameOfProduct;
	private String description;
	private double price;
	private long stockAvailable;

	@JsonIgnore
	@ManyToMany(mappedBy = "listOfAllProducts")
	private List<Cart> cart;

	// that means , all products belong to specific category
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Category catType;

	public Product() {
	};

	public Product(long id, String nameOfProduct, String description, double price, long stockAvailable,
			Category catType) {
		super();
		this.id = id;
		this.nameOfProduct = nameOfProduct;
		this.description = description;
		this.price = price;
		this.stockAvailable = stockAvailable;
		this.catType = catType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameOfProduct() {
		return nameOfProduct;
	}

	public void setNameOfProduct(String nameOfProduct) {
		this.nameOfProduct = nameOfProduct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(long stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public Category getCatType() {
		return catType;
	}

	public void setCatType(Category catType) {
		this.catType = catType;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", nameOfProduct=" + nameOfProduct + ", description=" + description + ", price="
				+ price + ", stockAvailable=" + stockAvailable;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

}
