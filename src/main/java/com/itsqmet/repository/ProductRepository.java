package com.itsqmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsqmet.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByNameContainingIgnoreCase(String name);

  List<Product> findByCategoryName(String categoryName);
}
