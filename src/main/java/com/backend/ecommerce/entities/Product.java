package com.backend.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product", schema = "ecomapp")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100)")
  private String name;

  @Column(name = "price", nullable = false, columnDefinition = "NUMERIC(10,2)")
  private double price;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @ElementCollection
  @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "image")
  private List<String> images;

  @Column(name = "color", nullable = false, columnDefinition = "VARCHAR(50)")
  private String color;

  @Column(name = "meta")
  private String meta;

  @Column(name = "rating", columnDefinition = "NUMERIC(2,1)")
  private float rating;

  @Column(name = "stock", columnDefinition = "INTEGER")
  private int stock;
}
