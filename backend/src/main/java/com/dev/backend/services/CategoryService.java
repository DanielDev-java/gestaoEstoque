package com.dev.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.backend.dtos.CategoryDTO;
import com.dev.backend.dtos.ProductDTO;
import com.dev.backend.entities.Category;
import com.dev.backend.projections.CategoryProjection;
import com.dev.backend.projections.ProductProjection;
import com.dev.backend.repositories.CategoryRepository;
import com.dev.backend.services.exceptions.DatabaseException;
import com.dev.backend.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Category insert(Category entity) {
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

    public Category update(Long id, Category category) {
        try {
            Category entity = repository.getReferenceById(id);
            updateData(entity, category);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<ProductDTO> findByCategory(Long idCategory) {
        List<ProductProjection> products = repository.searchByCategory(idCategory);
        List<ProductDTO> dto = products.stream().map(x -> new ProductDTO(x)).toList();
        return dto;
    }

    public List<CategoryDTO> findByNameCategory(String nameCategory) {
        List<CategoryProjection> categories = repository.searchByNameCategory(nameCategory);
        List<CategoryDTO> dto = categories.stream().map(x -> new CategoryDTO(x)).toList();
        return dto;
    }

    private void updateData(Category entitiy, Category category) {
        entitiy.setName(category.getName());
        entitiy.setDescription(category.getDescription());
    }
}
