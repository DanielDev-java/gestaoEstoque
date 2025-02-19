package com.dev.backend.dtos;

import java.time.LocalDate;

import com.dev.backend.projections.ProductSupplierProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductSupplierDTO {
    private Long idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private String unitMeasureProduct;
    private Double costPriceProduct;
    private Double salePriceProduct;
    private Integer minimumStockProduct;
    private Integer maximumStockProduct;
    private Integer quantityProduct;
    private LocalDate expirationDateProduct;
    private Long idSupplier;

    public ProductSupplierDTO(ProductSupplierProjection entity) {
        this.idProduct = entity.getIdProduct();
        this.nameProduct = entity.getNameProduct();
        this.descriptionProduct = entity.getDescriptionProduct();
        this.unitMeasureProduct = entity.getUnitMeasureProduct();
        this.costPriceProduct = entity.getCostPriceProduct();
        this.salePriceProduct = entity.getSalePriceProduct();
        this.minimumStockProduct = entity.getMinimumStockProduct();
        this.maximumStockProduct = entity.getMaximumStockProduct();
        this.quantityProduct = entity.getQuantityProduct();
        this.expirationDateProduct = entity.getExpirationDateProduct();
        this.idSupplier = entity.getIdSupplier();
    }

}
