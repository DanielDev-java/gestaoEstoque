package com.dev.backend.controllers;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.backend.dtos.ProductSupplierDTO;
import com.dev.backend.entities.Supplier;
import com.dev.backend.services.SupplierService;

@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService service;

    @GetMapping
    public ResponseEntity<List<Supplier>> findAll() {
        List<Supplier> entities = service.findAll();
        return ResponseEntity.ok().body(entities);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Supplier> findById(@PathVariable Long id) {
        Supplier entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    public ResponseEntity<Supplier> insert(@RequestBody Supplier entity) {
        entity = service.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Long id, @RequestBody Supplier entity) {
        entity = service.update(id, entity);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping(value = "/{idSupplier}/products")
    public ResponseEntity<List<ProductSupplierDTO>> findBySupplier(@PathVariable Long idSupplier) {
        List<ProductSupplierDTO> suppliers = service.findBySupplier(idSupplier);
        return ResponseEntity.ok().body(suppliers);
    }

}
