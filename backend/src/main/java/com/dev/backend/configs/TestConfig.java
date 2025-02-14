package com.dev.backend.configs;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dev.backend.entities.Product;
import com.dev.backend.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Product p1 = new Product(null, "Café", "Café Brasileiro", "g", 8.50, 17.00, 5, 85, 30,
                LocalDate.parse("2025-06-20"));
        productRepository.save(p1);
    }

}
