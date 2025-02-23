package com.dev.backend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;

    @OneToMany(mappedBy = "stock")
    private List<Product> products = new ArrayList<>();

    public Stock(Long id) {
        this.id = id;
    }

    public void reloadData() {
        Integer sum = 0;
        if (products.size() > 0) {
            for (Product product : products) {
                sum += product.getQuantity();
            }
        }
        this.quantity = sum;
    }
}
