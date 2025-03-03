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

import com.dev.backend.dtos.ProductDTO;
import com.dev.backend.entities.Batch;
import com.dev.backend.services.BatchService;

@RestController
@RequestMapping(value = "/batchs")
public class BatchController {
    @Autowired
    private BatchService service;

    @GetMapping
    public ResponseEntity<List<Batch>> findAll() {
        List<Batch> entities = service.findAll();
        return ResponseEntity.ok().body(entities);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Batch> findById(@PathVariable Long id) {
        Batch entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    public ResponseEntity<Batch> insert(@RequestBody Batch entity) {
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
    public ResponseEntity<Batch> update(@PathVariable Long id, @RequestBody Batch entity) {
        entity = service.update(id, entity);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping(value = "/{idBatch}/products")
    public ResponseEntity<List<ProductDTO>> findByBatch(@PathVariable Long idBatch) {
        List<ProductDTO> batchs = service.findByBatch(idBatch);
        return ResponseEntity.ok().body(batchs);
    }
}
