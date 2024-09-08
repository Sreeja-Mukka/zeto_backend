package com.example.zeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zeto.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
