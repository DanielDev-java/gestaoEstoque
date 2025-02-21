package com.dev.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.backend.entities.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

}
