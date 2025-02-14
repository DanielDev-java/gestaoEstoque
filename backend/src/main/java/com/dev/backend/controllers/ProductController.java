package com.dev.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.entities.Product;
import com.dev.backend.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> entities = service.findAll();
        return ResponseEntity.ok().body(entities);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product entities = service.findById(id);
        return ResponseEntity.ok().body(entities);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product entity) {
        entity = service.insert(entity);
        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product entity) {
        entity = service.update(id, entity);
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping(value = "/{id}/addStock/{quantity}")
    public ResponseEntity<Product> addStock(@PathVariable Long id, @PathVariable Integer quantity) {
        Product entity = service.addStock(id, quantity);
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping(value = "/{id}/removeStock/{quantity}")
    public ResponseEntity<Product> removeStock(@PathVariable Long id, @PathVariable Integer quantity) {
        Product entity = service.removeStock(id, quantity);
        return ResponseEntity.ok().body(entity);
    }
}
