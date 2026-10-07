package com.itsqmet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.itsqmet.entity.Product;
import com.itsqmet.service.ProductService;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Data
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public String getProducts(Model model, @RequestParam(value = "title", required = false) String title) {
    List<Product> products = productService.getProducts(title);
    model.addAttribute("title", title);
    model.addAttribute("products", products);
    return "index";
  }

}
