package com.dev.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.backend.entities.Supplier;
import com.dev.backend.projections.ProductProjection;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

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
            product_supplier ON product.id = product_supplier.id_product
        INNER JOIN 
            supplier ON product_supplier.id_supplier = supplier.id
        WHERE 
            supplier.id = :idSupplier
        ORDER BY product.name;
    """)
    List<ProductProjection> searchBySupplier(Long idSupplier);
}
