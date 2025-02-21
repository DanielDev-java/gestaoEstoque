package com.dev.backend.configs;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dev.backend.entities.Batch;
import com.dev.backend.entities.Category;
import com.dev.backend.entities.Product;
import com.dev.backend.entities.Supplier;
import com.dev.backend.repositories.BatchRepository;
import com.dev.backend.repositories.CategoryRepository;
import com.dev.backend.repositories.ProductRepository;
import com.dev.backend.repositories.SupplierRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Override
    public void run(String... args) throws Exception {
        Category c1 = new Category(null, "Café", "Category Cafe");
        categoryRepository.save(c1);

        Supplier s1 = new Supplier(null, "test", "(88) 998547-5893", "test@test.com");
        supplierRepository.save(s1);

        Batch b1 = new Batch(null, LocalDate.parse("2024-12-01"), LocalDate.parse("2025-11-25"), "Local fechado", true);
        Batch b2 = new Batch(null, LocalDate.parse("2024-07-01"), LocalDate.parse("2025-01-12"), "Longe de umidade", true);
        batchRepository.saveAll(Arrays.asList(b1,b2));

        Product p1 = new Product(null, "Café", "Café Brasileiro", "g", 8.50, 17.00, 5, 85, 30);
        Product p2 = new Product(null, "Cuscuz", "Cuscuz Brasileiro", "kg", 0.50, 3.00, 1, 50, 30);

        p1.getBatchs().add(b1);
        p1.getCategories().add(c1);
        p1.getSuppliers().add(s1);
        p2.getSuppliers().add(s1);
        p2.getBatchs().add(b2);
        p2.getCategories().add(c1);
        productRepository.saveAll(Arrays.asList(p1, p2));
    }

}
