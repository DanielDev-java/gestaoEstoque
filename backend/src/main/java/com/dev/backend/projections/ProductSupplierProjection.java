package com.dev.backend.projections;

import java.time.LocalDate;

public interface ProductSupplierProjection {

    Long getIdProduct();

    String getNameProduct();

    String getDescriptionProduct();

    String getUnitMeasureProduct();

    Double getCostPriceProduct();

    Double getSalePriceProduct();

    Integer getMinimumStockProduct();

    Integer getMaximumStockProduct();

    Integer getQuantityProduct();

    LocalDate getExpirationDateProduct();

    Long getIdSupplier();

}
