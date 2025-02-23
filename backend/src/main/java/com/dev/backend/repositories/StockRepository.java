package com.dev.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.backend.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
