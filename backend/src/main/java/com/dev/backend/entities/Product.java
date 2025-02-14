package com.dev.backend.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate expirationDate;

    public void addStock(Integer quantity) {
        this.quantity += quantity;
    }

    public void removeStock(Integer quantity) {
        this.quantity -= quantity;
    }
}
