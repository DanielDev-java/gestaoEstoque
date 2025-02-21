package com.dev.backend.projections;

public interface ProductProjection {

    Long getId();

    String getName();

    String getDescription();

    String getUnitMeasure();

    Double getCostPrice();

    Double getSalePrice();

    Integer getMinimumStock();

    Integer getMaximumStock();

    Integer getQuantity();

}
