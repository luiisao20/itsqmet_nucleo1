package com.itsqmet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El código del producto es obligatorio")
  private String code;

  @NotBlank(message = "El nombre del producto es obligatorio")
  private String name;

  @NotBlank(message = "La descripción del producto es obligatoria")
  private String description;

  @NotBlank(message = "El precio del producto es obligatorio")
  private Float price;

  private Float discount;

  private Integer stock;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
