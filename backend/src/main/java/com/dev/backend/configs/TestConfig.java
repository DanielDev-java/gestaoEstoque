package com.dev.backend.configs;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dev.backend.entities.Category;
import com.dev.backend.entities.Product;
import com.dev.backend.repositories.CategoryRepository;
import com.dev.backend.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Product p1 = new Product(null, "Café", "Café Brasileiro", "g", 8.50, 17.00, 5, 85, 30,
                LocalDate.parse("2025-06-20"));
        productRepository.save(p1);

        Category c1 = new Category(null, "Café", "Category Cafe");
        categoryRepository.save(c1);

        p1.getCategories().add(c1);
        productRepository.save(p1);
    }

}
