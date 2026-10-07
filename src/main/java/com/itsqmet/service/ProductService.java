package com.itsqmet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itsqmet.entity.Product;
import com.itsqmet.repository.ProductRepository;

import lombok.Data;

@Service
@Data
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> getProducts(String title) {
    if (title == null || title.trim().isEmpty())
      return productRepository.findAll();

    return productRepository.findByNameContainingIgnoreCase(title);
  }

  public List<Product> getProductsByCategory(String categoryName) {
    return productRepository.findByCategoryName(categoryName);
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, Product product) {
    Product oldProduct = getProductById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    oldProduct.setName(product.getName());
    oldProduct.setCode(product.getCode());
    oldProduct.setDescription(product.getDescription());
    oldProduct.setCategory(product.getCategory());
    oldProduct.setPrice(product.getPrice());
    oldProduct.setDiscount(product.getDiscount());
    oldProduct.setStock(product.getStock());

    return productRepository.save(oldProduct);
  }

  public void deleteProduct(Long id) {
    Product product = getProductById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    productRepository.delete(product);
  }
}
