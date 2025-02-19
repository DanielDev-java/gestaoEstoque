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

import com.dev.backend.dtos.CategoryDTO;
import com.dev.backend.dtos.ProductDTO;
import com.dev.backend.entities.Category;
import com.dev.backend.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> entities = service.findAll();
        return ResponseEntity.ok().body(entities);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category entity) {
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
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category entity) {
        entity = service.update(id, entity);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping(value = "/{idCategory}/products")
    public ResponseEntity<List<ProductDTO>> findByCategory(@PathVariable Long idCategory) {
        List<ProductDTO> products = service.findByCategory(idCategory);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/names/{nameCategory}")
    public ResponseEntity<List<CategoryDTO>> findByNameCategory(@PathVariable String nameCategory) {
        List<CategoryDTO> categories = service.findByNameCategory(nameCategory);
        return ResponseEntity.ok().body(categories);
    }

}
