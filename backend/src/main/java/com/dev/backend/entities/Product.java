package com.dev.backend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String unitMeasure;
    private Double costPrice;
    private Double salePrice;
    private Integer minimumStock;
    private Integer maximumStock;
    private Integer quantity;

    @ManyToMany
    @JoinTable(
        name = "product_category", 
        joinColumns = @JoinColumn(name = "id_product"), 
        inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    @Setter(value = AccessLevel.NONE)
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "product_supplier", 
        joinColumns = @JoinColumn(name = "id_product"), 
        inverseJoinColumns = @JoinColumn(name = "id_supplier")
    )
    @Setter(value = AccessLevel.NONE)
    private Set<Supplier> suppliers = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "product_batch", 
        joinColumns = @JoinColumn(name = "id_product"), 
        inverseJoinColumns = @JoinColumn(name = "id_batch")
    )
    @Setter(value = AccessLevel.NONE)
    private List<Batch> batchs = new ArrayList<>();

    public Product(Long id, String name, String description, String unitMeasure, Double costPrice, Double salePrice,
            Integer minimumStock, Integer maximumStock, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitMeasure = unitMeasure;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.minimumStock = minimumStock;
        this.maximumStock = maximumStock;
        this.quantity = quantity;
    }

    public void addStock(Integer quantity) {
        this.quantity += quantity;
    }

    public void removeStock(Integer quantity) {
        this.quantity -= quantity;
    }
}
