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
  @GeneratedValue
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private double price;

  @Column(name = "description")
  private String description;

  @ElementCollection
  @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "image")
  private List<String> images;

  @Column(name = "color")
  private String color;

  //  @Type(type = "jsonb")
  //  @Column(name = "meta", columnDefinition = "jsonb")
  //  private Map<String, Object> meta;

  @Column(name = "rating")
  private float rating;

  @Column(name = "stock")
  private int stock;
}
