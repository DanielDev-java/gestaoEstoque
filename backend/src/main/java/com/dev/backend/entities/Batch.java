package com.dev.backend.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "batch")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Batch implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate manufactureDate;
    private LocalDate expirationDate;
    private String storageConditions;
    @Getter(value = AccessLevel.NONE)
    private boolean approvedQuality;

    @JsonIgnore
    @Setter(value = AccessLevel.NONE)
    @ManyToMany(mappedBy = "batchs")
    private List<Product> products = new ArrayList<>();

    public Batch(Long id, LocalDate manufactureDate, LocalDate expirationDate, String storageConditions,
            boolean approvedQuality) {
        this.id = id;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.storageConditions = storageConditions;
    }

    public boolean getApprovedQuality() {
        return approvedQuality;
    }

    
}