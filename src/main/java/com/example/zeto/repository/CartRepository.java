package com.example.zeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zeto.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
