package com.dev.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.backend.dtos.ProductDTO;
import com.dev.backend.entities.Batch;
import com.dev.backend.projections.ProductProjection;
import com.dev.backend.repositories.BatchRepository;
import com.dev.backend.services.exceptions.DatabaseException;
import com.dev.backend.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BatchService {
    @Autowired
    private BatchRepository repository;

    public List<Batch> findAll() {
        return repository.findAll();
    }

    public Batch findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Batch insert(Batch entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Batch update(Long id, Batch batch) {
        try {
            Batch entity = repository.getReferenceById(id);
            updateData(entity, batch);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<ProductDTO> findByBatch(Long idBatch) {
        List<ProductProjection> batchs = repository.searchByBatch(idBatch);
        List<ProductDTO> dto = batchs.stream().map(x -> new ProductDTO(x)).toList();
        return dto;
    }

    private void updateData(Batch entitiy, Batch batch) {
        entitiy.setApprovedQuality(batch.getApprovedQuality());
        entitiy.setStorageConditions(batch.getStorageConditions());
    }
}
