package com.dev.backend.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.backend.entities.Stock;
import com.dev.backend.services.StockService;

@RestController
@RequestMapping(value = "/stocks")
public class StockController {
    @Autowired
    private StockService service;

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {
        List<Stock> entities = service.findAll();
        return ResponseEntity.ok().body(entities);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Stock> findById(@PathVariable Long id) {
        Stock entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    public ResponseEntity<Stock> insert(@RequestBody Stock entity) {
        entity = service.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
