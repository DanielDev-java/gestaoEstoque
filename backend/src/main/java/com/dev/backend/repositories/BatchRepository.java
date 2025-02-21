package com.dev.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.backend.entities.Batch;
import com.dev.backend.projections.ProductProjection;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    @Query(nativeQuery = true, value = """
        SELECT
            product.id, product.name,
            product.description, product.unit_measure,
            product.cost_price, product.sale_price,
            product.minimum_stock, product.maximum_stock,
            product.quantity
        FROM 
            product
        INNER JOIN 
            product_batch ON product.id = product_batch.id_product
        INNER JOIN 
            batch ON product_batch.id_batch = batch.id
        WHERE 
            batch.id = :idBatch
        ORDER BY product.name;
    """)
    List<ProductProjection> searchByBatch(Long idBatch);

}
