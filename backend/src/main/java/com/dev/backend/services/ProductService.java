package com.dev.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.Product;
import com.dev.backend.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).get();
    }

    public Product insert(Product entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Product update(Long id, Product product) {
        Product entity = repository.getReferenceById(id);
        updateData(entity, product);
        return repository.save(entity);
    }

    public Product addStock(Long id, Integer quantity) {
        Product entity = repository.findById(id).get();
        entity.addStock(quantity);
        return repository.save(entity);
    }

    public Product removeStock(Long id, Integer quantity) {
        Product entity = repository.findById(id).get();
        entity.removeStock(quantity);
        return repository.save(entity);
    }

    private void updateData(Product entitiy, Product product) {
        entitiy.setDescription(product.getDescription());
        entitiy.setCostPrice(product.getCostPrice());
        entitiy.setSalePrice(product.getSalePrice());
        entitiy.setMinimumStock(product.getMinimumStock());
        entitiy.setMaximumStock(product.getMaximumStock());
    }
}
