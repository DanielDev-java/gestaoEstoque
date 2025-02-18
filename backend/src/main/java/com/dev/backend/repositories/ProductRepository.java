package com.dev.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.backend.entities.Product;
import com.dev.backend.projections.ProductProjection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = """
                SELECT * FROM
                    product
                WHERE
                    UPPER(product.name)
                LIKE UPPER(CONCAT('%', :nameProduct, '%'))
            """)
    List<ProductProjection> searchByNameProduct(String nameProduct);

}
