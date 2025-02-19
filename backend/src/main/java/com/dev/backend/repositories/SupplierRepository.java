package com.dev.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.backend.entities.Supplier;
import com.dev.backend.projections.ProductSupplierProjection;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(nativeQuery = true, value = """
        SELECT
            product.id AS id_product,
            product.name AS name_product,
            product.description AS description_product,
            product.unit_measure AS unit_measure_product,
            product.cost_price AS cost_price_product,
            product.sale_price AS sale_price_product,
            product.minimum_stock AS minimum_stock_product,
            product.maximum_stock AS maximum_stock_product,
            product.quantity AS quantity_product,
            product.expiration_date AS expiration_date_product,
            supplier.id AS id_supplier
        FROM product
        INNER JOIN product_supplier ON product.id = product_supplier.id_product
        INNER JOIN supplier ON product_supplier.id_supplier = supplier.id
        WHERE supplier.id = :idSupplier
        ORDER BY product.name;
    """)
    List<ProductSupplierProjection> searchBySupplier(Long idSupplier);
}
