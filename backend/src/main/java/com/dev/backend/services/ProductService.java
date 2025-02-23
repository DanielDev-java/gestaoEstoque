package com.dev.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.backend.dtos.ProductDTO;
import com.dev.backend.entities.Product;
import com.dev.backend.entities.Stock;
import com.dev.backend.projections.ProductProjection;
import com.dev.backend.repositories.ProductRepository;
import com.dev.backend.repositories.StockRepository;
import com.dev.backend.services.exceptions.DatabaseException;
import com.dev.backend.services.exceptions.InvalidQuantityException;
import com.dev.backend.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private StockRepository stockRepository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Product insert(Product entity) {
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

    public Product update(Long id, Product product) {
        try {
            Product entity = repository.getReferenceById(id);
            updateData(entity, product);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Product addStock(Long id, Integer quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("The quantity must be greater than zero.");
        }
        Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        if (entity.getQuantity() + quantity > entity.getMaximumStock()) {
            throw new InvalidQuantityException("Stock maximum value exceeded.");
        }
        entity.addStock(quantity);
        Stock stock = entity.getStock();
        stock.reloadData();
        stockRepository.save(stock);
        return repository.save(entity);
    }

    public Product removeStock(Long id, Integer quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("The quantity must be greater than zero.");
        }
        Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        if (entity.getQuantity() - quantity < entity.getMinimumStock()) {
            throw new InvalidQuantityException("Stock minimum value exceeded.");
        }
        entity.removeStock(quantity);
        Stock stock = entity.getStock();
        stock.reloadData();
        stockRepository.save(stock);
        return repository.save(entity);
    }

    public List<ProductDTO> findByNameProduct(String nameProduct) {
        List<ProductProjection> suppliers = repository.searchByNameProduct(nameProduct);
        List<ProductDTO> dto = suppliers.stream().map(x -> new ProductDTO(x)).toList();
        return dto;
    }

    private void updateData(Product entitiy, Product product) {
        entitiy.setDescription(product.getDescription());
        entitiy.setCostPrice(product.getCostPrice());
        entitiy.setSalePrice(product.getSalePrice());
        entitiy.setMinimumStock(product.getMinimumStock());
        entitiy.setMaximumStock(product.getMaximumStock());
    }
}
