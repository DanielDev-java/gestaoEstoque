package com.dev.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.backend.entities.Category;
import com.dev.backend.projections.ProductProjection;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value = """
        SELECT
            product.id, product.name,
        	product.description, product.unit_measure,
            product.cost_price, product.sale_price,
            product.minimum_stock, product.maximum_stock,
            product.quantity, product.expiration_date
        FROM
        	product
        INNER JOIN
        	product_category ON product.id = product_category.id_product
        INNER JOIN
        	category ON product_category.id_category = category.id
        WHERE
        	category.id = :idCategory
        ORDER BY product.name;
    """)
    List<ProductProjection> searchByCategory(Long idCategory);

}
