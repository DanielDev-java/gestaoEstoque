package com.dev.backend.projections;

import java.time.LocalDate;

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

    LocalDate getExpirationDate();
}
